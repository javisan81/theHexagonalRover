package org.bcnjug.infrastructure;

import org.bcnjug.domain.MarsRoverUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.bcnjug.domain.Direction.North;
import static org.bcnjug.domain.Direction.South;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarsRoverIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarsRoverUseCase marsRoverUseCase;

    @Test
    public void smokeTest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isNotFound());
    }

    @Test
    public void initialiseRoverTo11FacingNorth() throws Exception {
        Position x1y1 = new Position(1, 1);
        when(marsRoverUseCase.getDirection()).thenReturn(North);
        when(marsRoverUseCase.getPosition()).thenReturn(x1y1);
        setRoverPositionDirection(x1y1, "N");
        assertRoverIsInPosition(x1y1);
        assertRoverIsFacing("N");
    }
    @Test
    public void initialiseRoverTo22FacingSouth() throws Exception {
        Position x2y2 = new Position(2, 2);
        when(marsRoverUseCase.getDirection()).thenReturn(South);
        when(marsRoverUseCase.getPosition()).thenReturn(x2y2);
        setRoverPositionDirection(x2y2, "S");
        assertRoverIsInPosition(x2y2);
        assertRoverIsFacing("S");
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
                                                    "x": %d,
                                                    "y": %d
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
                                "x": %d,
                                "y": %d
                            }
                        """.formatted(position.x(), position.y())));
    }

}
