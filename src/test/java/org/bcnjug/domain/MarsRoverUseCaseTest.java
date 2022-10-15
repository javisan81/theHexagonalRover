package org.bcnjug.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.bcnjug.domain.Direction.*;
import static org.bcnjug.domain.MoveCommand.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverUseCaseTest {

    private static Stream<Arguments> initialPositionDirections() {
        return Stream.of(
                Arguments.of(new PositionDirection(new Position(1, 1), North)),
                Arguments.of(new PositionDirection(new Position(2, 2), South)),
                Arguments.of(new PositionDirection(new Position(1, 1), East)),
                Arguments.of(new PositionDirection(new Position(2, 2), West))
        );
    }

    @ParameterizedTest
    @MethodSource("initialPositionDirections")
    public void initialiseRover(PositionDirection positionDirection) {
        MarsRoverUseCase marsRover = new MarsRover();
        marsRover.setPosition(positionDirection);
        assertEquals(marsRover.getPosition(), positionDirection.position());
        assertEquals(marsRover.getDirection(), positionDirection.direction());
    }

    private static Stream<Arguments> moveForwardRoversDirection() {
        return Stream.of(
                Arguments.of(North, new Position(0, 1)),
                Arguments.of(South, new Position(0, -1)),
                Arguments.of(East, new Position(1, 0)),
                Arguments.of(West, new Position(-1, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("moveForwardRoversDirection")
    public void moveForward(Direction direction, Position expectedPosition) {
        MarsRoverUseCase marsRover = new MarsRover();
        marsRover.setPosition(new PositionDirection(new Position(0, 0), direction));
        marsRover.move(List.of(Forward));
        assertEquals(marsRover.getPosition(), expectedPosition);
        assertEquals(marsRover.getDirection(), direction);
    }

    private static Stream<Arguments> moveBackwardRoversDirection() {
        return Stream.of(
                Arguments.of(North, new Position(0, -1)),
                Arguments.of(South, new Position(0, 1)),
                Arguments.of(East, new Position(-1, 0)),
                Arguments.of(West, new Position(1, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("moveBackwardRoversDirection")
    public void moveBackward(Direction direction, Position expectedPosition) {
        MarsRoverUseCase marsRover = new MarsRover();
        marsRover.setPosition(new PositionDirection(new Position(0, 0), direction));
        marsRover.move(List.of(Backward));
        assertEquals(marsRover.getPosition(), expectedPosition);
        assertEquals(marsRover.getDirection(), direction);
    }

    private static Stream<Arguments> turnLeftRoversDirection() {
        return Stream.of(
                Arguments.of(North, West),
                Arguments.of(South, East),
                Arguments.of(East, North),
                Arguments.of(West, South)
        );
    }

    @ParameterizedTest
    @MethodSource("turnLeftRoversDirection")
    public void turnLeft(Direction currentDirection, Direction expectedDirection) {
        MarsRoverUseCase marsRover = new MarsRover();
        marsRover.setPosition(new PositionDirection(new Position(0, 0), currentDirection));
        marsRover.move(List.of(Left));
        assertEquals(marsRover.getDirection(), expectedDirection);
    }


    private static Stream<Arguments> turnRightRoversDirection() {
        return Stream.of(
                Arguments.of(North, East),
                Arguments.of(South, West),
                Arguments.of(East, South),
                Arguments.of(West, North)
        );
    }

    @ParameterizedTest
    @MethodSource("turnRightRoversDirection")
    public void turnRight(Direction currentDirection, Direction expectedDirection) {
        MarsRoverUseCase marsRover = new MarsRover();
        marsRover.setPosition(new PositionDirection(new Position(0, 0), currentDirection));
        marsRover.move(List.of(Right));
        assertEquals(marsRover.getDirection(), expectedDirection);
    }
}
