package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.PositionDirectionRepository;
import org.bcnjug.domain.RoverNotInitializedException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.bcnjug.domain.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class PositionDirectionRepositoryIT {
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
    public void saveAndRetrievePositionDirection(PositionDirection positionDirection) {
        PositionDirectionRepository positionDirectionRepository = getPositionDirectionRepositoryUnderTest();
        assertThrows(RoverNotInitializedException.class, positionDirectionRepository::get);
        positionDirectionRepository.save(positionDirection);
        assertEquals(positionDirection, positionDirectionRepository.get());
    }

    protected abstract PositionDirectionRepository getPositionDirectionRepositoryUnderTest();
}