package org.bcnjug.infrastructure.repositories;

import org.bcnjug.domain.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestPositionDirectoryRepository implements PositionDirectionRepository {
    public static class RestPositionDirection {
        public int x;
        public int y;
        public String direction;
    }
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
            RestPositionDirection restPositionDirection = restTemplate.getForObject("/position", RestPositionDirection.class);
            return new PositionDirection(new Position(restPositionDirection.x, restPositionDirection.y), Direction.valueOf(restPositionDirection.direction));
        }catch (HttpClientErrorException.NotFound error) {
            throw new RoverNotInitializedException();
        }
    }
}
