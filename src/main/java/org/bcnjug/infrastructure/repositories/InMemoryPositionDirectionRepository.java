package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.PositionDirectionRepository;

public class InMemoryPositionDirectionRepository implements PositionDirectionRepository {
    private PositionDirection positionDirection;

    @Override
    public void save(PositionDirection positionDirection) {
        this.positionDirection = positionDirection;
    }

    @Override
    public PositionDirection get() {
        return positionDirection;
    }
}
