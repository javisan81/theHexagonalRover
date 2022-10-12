package org.bcnjug;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.bcnjug.Direction.North;
import static org.bcnjug.Direction.South;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarsRoverSmokeIT {

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
        when(marsRoverUseCase.getDirection()).thenReturn(North);
        when(marsRoverUseCase.getPosition()).thenReturn(new Position(1,1));
        mockMvc.perform(
                        post("/initialize")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                            {
                                                "position": {
                                                    "x": 1,
                                                    "y": 1
                                                },
                                                "direction": "N"
                                            }
                                        """))
                .andExpect(status().isOk());

        mockMvc.perform(
                        get("/position")
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "x": 1,
                                "y": 1
                            }
                        """));

        mockMvc.perform(
                        get("/direction")
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                        {"direction":  "N"}
                        """));
    }
    @Test
    public void initialiseRoverTo22FacingSouth() throws Exception {
        when(marsRoverUseCase.getDirection()).thenReturn(South);
        when(marsRoverUseCase.getPosition()).thenReturn(new Position(2,2));
        mockMvc.perform(
                        post("/initialize")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                            {
                                                "position": {
                                                    "x": 2,
                                                    "y": 2
                                                },
                                                "direction": "S"
                                            }
                                        """))
                .andExpect(status().isOk());

        mockMvc.perform(
                        get("/position")
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "x": 2,
                                "y": 2
                            }
                        """));

        mockMvc.perform(
                        get("/direction")
                ).andExpect(status().isOk())
                .andExpect(content().json("""
                        {"direction":  "S"}
                        """));
    }

}
