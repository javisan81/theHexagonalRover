package org.bcnjug;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MarsRoverSmokeIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void smokeTest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isNotFound());
    }

    @Test
    public void initialiseRoverTo11FacingNorth() throws Exception {
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
                        {"direction":  "North"}
                        """));
    }

}
