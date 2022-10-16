package org.bcnjug.infrastructure.configurations;

import org.bcnjug.domain.MarsRover;
import org.bcnjug.infrastructure.repositories.JPAPositionDirectionRepository;
import org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarsRoverConfiguration {


    @Bean
    public MarsRover marsRover(JPAPositionDirectionRepository jpaPositionDirectionRepository) {
        return new MarsRover(jpaPositionDirectionRepository);
    }

    @Bean
    public JPAPositionDirectionRepository jpaPositionDirectionRepository(PositionDirectionRepository positionDirectionRepository) {
        return new JPAPositionDirectionRepository(positionDirectionRepository);
    }
}
