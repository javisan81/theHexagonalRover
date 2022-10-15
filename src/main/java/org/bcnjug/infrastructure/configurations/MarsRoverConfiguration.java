package org.bcnjug.infrastructure.configurations;

import org.bcnjug.domain.MarsRover;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarsRoverConfiguration {
    @Bean
    public MarsRover marsRover() {
        return new MarsRover();
    }
}
