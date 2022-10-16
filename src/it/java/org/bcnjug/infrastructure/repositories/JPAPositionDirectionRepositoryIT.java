package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.PositionDirectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPAPositionDirectionRepositoryIT extends PositionDirectionRepositoryIT {
    @Autowired
    private JPAPositionDirectionRepository jpaPositionDirectionRepository;
    @Autowired
    private org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository positionDirectionCrudRepository;

    @BeforeEach
    public void setup() {
        try {
            positionDirectionCrudRepository.deleteById("rover1");
        } catch (Exception ignored) {

        }
    }

    @Override
    protected PositionDirectionRepository getPositionDirectionRepositoryUnderTest() {
        return jpaPositionDirectionRepository;
    }
}
