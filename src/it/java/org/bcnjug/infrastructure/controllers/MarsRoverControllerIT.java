package org.bcnjug.infrastructure.controllers;

import org.bcnjug.domain.MarsRoverUseCase;
import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.bcnjug.domain.Direction.*;
import static org.bcnjug.domain.MoveCommand.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarsRoverControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarsRoverUseCase marsRoverUseCase;

    private static Stream<Arguments> initialPositionDirections() {
        return Stream.of(
                Arguments.of(new PositionDirection(new Position(1, 1), North), "N"),
                Arguments.of(new PositionDirection(new Position(2, 2), South), "S"),
                Arguments.of(new PositionDirection(new Position(1, 1), East), "E"),
                Arguments.of(new PositionDirection(new Position(2, 2), West), "W")
        );
    }

    @ParameterizedTest
    @MethodSource("initialPositionDirections")
    public void initialiseRover(PositionDirection positionDirection, String formattedDirection) throws Exception {
        when(marsRoverUseCase.getDirection()).thenReturn(positionDirection.direction());
        when(marsRoverUseCase.getPosition()).thenReturn(positionDirection.position());
        setRoverPositionDirection(positionDirection.position(), formattedDirection);
        assertRoverIsInPosition(positionDirection.position());
        assertRoverIsFacing(formattedDirection);
        verify(marsRoverUseCase).setPosition(positionDirection);
    }

    @Test
    public void moveRover() throws Exception {
        when(marsRoverUseCase.getDirection()).thenReturn(North);
        when(marsRoverUseCase.getPosition()).thenReturn(new Position(0, 0));
        setRoverPositionDirection(new Position(0, 0), "N");
        moveCommand(new String[]{"f", "b", "r", "l"});
        assertRoverIsInPosition(new Position(0, 0));
        assertRoverIsFacing("N");
        verify(marsRoverUseCase).move(asList(Forward, Backward, Right, Left));
    }

    private void moveCommand(String[] moveCommands) throws Exception {
        String jsonCommandsArray = Stream.of(moveCommands).map("\"%s\""::formatted).collect(Collectors.joining(","));
        mockMvc.perform(
                        post("/move")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        [%s]
                                        """.formatted(jsonCommandsArray)))
                .andExpect(status().isOk());
    }


    private void assertRoverIsFacing(String direction) throws Exception {
        mockMvc.perform(
                        get("/direction")
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                        {"direction":  "%s"}
                        """.formatted(direction)));
    }


    private void setRoverPositionDirection(Position position, String direction) throws Exception {
        mockMvc.perform(
                        post("/initialize")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                            {
                                                "position": {
                                                    "lon": %d,
                                                    "lat": %d
                                                },
                                                "direction": "%s"
                                            }
                                        """.formatted(position.x(), position.y(), direction)))
                .andExpect(status().isOk());
    }

    private void assertRoverIsInPosition(Position position) throws Exception {
        mockMvc.perform(
                        get("/position")
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "lon": %d,
                                "lat": %d
                            }
                        """.formatted(position.x(), position.y())));
    }

}
