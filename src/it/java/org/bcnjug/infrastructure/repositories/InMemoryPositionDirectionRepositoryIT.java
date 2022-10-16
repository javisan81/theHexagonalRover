package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.PositionDirectionRepository;

public class InMemoryPositionDirectionRepositoryIT extends PositionDirectionRepositoryIT {
    @Override
    protected PositionDirectionRepository getPositionDirectionRepositoryUnderTest() {
        return new InMemoryPositionDirectionRepository();
    }
}
