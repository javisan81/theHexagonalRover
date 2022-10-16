package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.Direction;
import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.PositionDirectionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

public class JPAPositionDirectionRepository implements PositionDirectionRepository {
    private final org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository jpaPositionDirectionRepository;

    public JPAPositionDirectionRepository(org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository jpaPositionDirectionRepository) {
        this.jpaPositionDirectionRepository = jpaPositionDirectionRepository;
    }

    @Override
    @Transactional
    public void save(PositionDirection positionDirection) {
        jpaPositionDirectionRepository.save(new org.bcnjug.infrastructure.repositories.jpa.PositionDirection("rover1", positionDirection.position().x(), positionDirection.position().y(), positionDirection.direction().toString()));
    }

    @Override
    @Transactional
    public PositionDirection get() {
        return jpaPositionDirectionRepository.findById("rover1")
                .map(e -> new PositionDirection(new Position(e.getX(), e.getY()), Direction.valueOf(e.getCoordinate())))
                .orElse(null);
    }
}
