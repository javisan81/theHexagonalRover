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
    public Position getPosition() {
        return marsRoverUseCase.getPosition();
    }

    @GetMapping("/direction")
    public org.bcnjug.infrastructure.Direction getDirection() {
        return new org.bcnjug.infrastructure.Direction(marsRoverUseCase.getDirection());
    }
}
