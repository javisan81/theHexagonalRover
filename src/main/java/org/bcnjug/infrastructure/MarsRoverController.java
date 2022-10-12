package org.bcnjug.infrastructure;

import org.bcnjug.domain.Direction;
import org.bcnjug.domain.MarsRoverUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarsRoverController {
    private final MarsRoverUseCase marsRoverUseCase;

    public MarsRoverController(MarsRoverUseCase marsRoverUseCase) {
        this.marsRoverUseCase = marsRoverUseCase;
    }

    @PostMapping("/initialize")
    public void setPosition(@RequestBody PositionDirection position) {
        marsRoverUseCase.setPosition(position);
    }

    @GetMapping("/position")
    public String getPosition() {
        return toJson(marsRoverUseCase.getPosition());
    }

    private String toJson(Position position) {
        return """
                {"x":%d, "y":%d}
                """.formatted(position.getX(), position.getY());
    }

    @GetMapping("/direction")
    public String getDirection() {
        return toJson(marsRoverUseCase.getDirection());
    }

    private String toJson(Direction direction) {
        return switch (direction) {
            case North -> toJsonDirection("N");
            case South -> toJsonDirection("S");
        };
    }

    private static String toJsonDirection(String direction) {
        return """
                {
                    "direction": %s
                }""".formatted(direction);
    }
}
