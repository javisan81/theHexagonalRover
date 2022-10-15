package org.bcnjug.infrastructure.controllers;

import org.bcnjug.domain.Direction;
import org.bcnjug.domain.MarsRoverUseCase;
import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.infrastructure.controllers.Coordinate;
import org.bcnjug.infrastructure.controllers.JsonDirection;
import org.bcnjug.infrastructure.controllers.LatLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.bcnjug.domain.Direction.*;

@RestController
public class MarsRoverController {
    private final MarsRoverUseCase marsRoverUseCase;

    public MarsRoverController(MarsRoverUseCase marsRoverUseCase) {
        this.marsRoverUseCase = marsRoverUseCase;
    }

    @PostMapping("/initialize")
    public void setPosition(@RequestBody Coordinate position) {
        marsRoverUseCase.setPosition(toPositionDirection(position));
    }

    @GetMapping("/position")
    public LatLong getPosition() {
        Position position = marsRoverUseCase.getPosition();
        return new LatLong(position.x(), position.y());
    }

    @GetMapping("/direction")
    public JsonDirection getDirection() {
        return new JsonDirection(marsRoverUseCase.getDirection());
    }

    private static Direction toDirection(String direction) {
        return switch (direction) {
            case "N" -> North;
            case "S" -> South;
            case "E" -> East;
            case "W" -> West;
            default -> North;
        };
    }

    private static PositionDirection toPositionDirection(Coordinate position) {
        return new PositionDirection(
                new Position(position.position().lon(), position.position().lat()),
                toDirection(position.direction()));
    }

}
