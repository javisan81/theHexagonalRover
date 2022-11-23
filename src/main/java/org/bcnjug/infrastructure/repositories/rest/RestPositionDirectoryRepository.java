package org.bcnjug.infrastructure.repositories.rest;

import org.bcnjug.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestPositionDirectoryRepository implements PositionDirectionRepository {
    public static class RestPositionDirection {
        public int x;
        public int y;
        public String direction;

        public RestPositionDirection() {
        }

        public RestPositionDirection(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    private final RestTemplate restTemplate;

    public RestPositionDirectoryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void save(PositionDirection positionDirection) {
        HttpEntity<RestPositionDirection> httpEntity =  new HttpEntity<>(new RestPositionDirection(positionDirection.position().x(), positionDirection.position().y(), positionDirection.direction().toString()));
        restTemplate.postForObject("/repository/position", httpEntity, String.class);
    }

    @Override
    public PositionDirection get() {
        try{
            RestPositionDirection restPositionDirection = restTemplate.getForObject("/repository/position", RestPositionDirection.class);
            return new PositionDirection(new Position(restPositionDirection.x, restPositionDirection.y), Direction.valueOf(restPositionDirection.direction));
        }catch (HttpClientErrorException.NotFound error) {
            throw new RoverNotInitializedException();
        }
    }
}
