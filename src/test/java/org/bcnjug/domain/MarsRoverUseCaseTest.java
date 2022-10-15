package org.bcnjug.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.bcnjug.domain.Direction.*;
import static org.bcnjug.domain.Direction.West;
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
}
