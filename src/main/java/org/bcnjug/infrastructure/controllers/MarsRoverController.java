package org.bcnjug.infrastructure.controllers;

import org.bcnjug.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

import static org.bcnjug.domain.Direction.*;
import static org.bcnjug.domain.MoveCommand.*;

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

    @PostMapping("/move")
    public void move(@RequestBody String[] commands) {
        marsRoverUseCase.move(toMoveCommands(commands));
    }

    private List<MoveCommand> toMoveCommands(String[] commands) {
        return Stream.of(commands).map(c -> switch (c){
            case "f" -> Forward;
            case "b" -> Backward;
            case "r" -> Right;
            case "l" -> Left;
            default -> Forward;
        }).toList();
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
