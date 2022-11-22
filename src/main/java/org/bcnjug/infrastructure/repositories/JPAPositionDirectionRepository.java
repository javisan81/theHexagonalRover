package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.*;

import javax.transaction.Transactional;

public class JPAPositionDirectionRepository implements PositionDirectionRepository {
    public static final String ROVER_NAME = "rover1";
    private final org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository jpaPositionDirectionRepository;

    public JPAPositionDirectionRepository(org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository jpaPositionDirectionRepository) {
        this.jpaPositionDirectionRepository = jpaPositionDirectionRepository;
    }

    @Override
    @Transactional
    public void save(PositionDirection positionDirection) {
        jpaPositionDirectionRepository.save(
                new org.bcnjug.infrastructure.repositories.jpa.PositionDirection(
                        ROVER_NAME,
                        positionDirection.position().x(),
                        positionDirection.position().y(),
                        positionDirection.direction().toString()
                )
        );
    }

    @Override
    @Transactional
    public PositionDirection get() {
        return jpaPositionDirectionRepository.findById(ROVER_NAME)
                .map(e -> new PositionDirection(new Position(e.x, e.y), Direction.valueOf(e.coordinate)))
                .orElseThrow(RoverNotInitializedException::new);
    }
}
