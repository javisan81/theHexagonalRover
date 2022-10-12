package org.bcnjug;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarsRoverController {
    @PostMapping("/initialize")
    public void setPosition(@RequestBody PositionDirection position) {

    }

    @GetMapping("/position")
    public String getPosition() {
        return """
                {
                "x":1,
                "y":1
                }
                """;
    }
    @GetMapping("/direction")
    public String getDirection() {
        return """
                {
                "direction":"North"
                }
                """;
    }
}
