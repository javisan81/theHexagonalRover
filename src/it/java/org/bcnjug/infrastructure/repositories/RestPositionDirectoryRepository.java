package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.PositionDirectionRepository;
import org.bcnjug.domain.RoverNotInitializedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestPositionDirectoryRepository implements PositionDirectionRepository {
    private final RestTemplate restTemplate;

    public RestPositionDirectoryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(PositionDirection positionDirection) {

    }

    @Override
    public PositionDirection get() {
        try{
            restTemplate.getForObject("/position", String.class);
            return null;
        }catch (HttpClientErrorException.NotFound error) {
            throw new RoverNotInitializedException();
        }
    }
}
