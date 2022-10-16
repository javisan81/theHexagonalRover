package org.bcnjug.infrastructure.configurations;

import org.bcnjug.domain.MarsRover;
import org.bcnjug.infrastructure.repositories.InMemoryPositionDirectionRepository;
import org.bcnjug.infrastructure.repositories.JPAPositionDirectionRepository;
import org.bcnjug.infrastructure.repositories.jpa.PositionDirectionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarsRoverConfiguration {


    @Bean
    public MarsRover marsRover() {
        return new MarsRover(new InMemoryPositionDirectionRepository());
    }

    @Bean
    public JPAPositionDirectionRepository jpaPositionDirectionRepository(PositionDirectionRepository positionDirectionRepository) {
        return new JPAPositionDirectionRepository(positionDirectionRepository);
    }
}
