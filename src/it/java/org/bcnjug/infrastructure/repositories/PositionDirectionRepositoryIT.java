package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.PositionDirectionRepository;
import org.junit.jupiter.api.Test;

import static org.bcnjug.domain.Direction.North;
import static org.junit.jupiter.api.Assertions.*;

abstract class PositionDirectionRepositoryIT {
    @Test
    public void saveAndRetrieveOnePositionDirection(){
        PositionDirectionRepository positionDirectionRepository=getPositionDirectionRepositoryUnderTest();
        assertNull(positionDirectionRepository.get());
        positionDirectionRepository.save(new PositionDirection(new Position(1,1), North));
        assertEquals(new PositionDirection(new Position(1,1), North), positionDirectionRepository.get());
    }

    @Test
    public void saveAndRetrieveAnotherPositionDirection(){
        PositionDirectionRepository positionDirectionRepository=getPositionDirectionRepositoryUnderTest();
        assertNull(positionDirectionRepository.get());
        positionDirectionRepository.save(new PositionDirection(new Position(1,1), North));
        assertEquals(new PositionDirection(new Position(1,1), North), positionDirectionRepository.get());
    }

    protected abstract PositionDirectionRepository getPositionDirectionRepositoryUnderTest();
}