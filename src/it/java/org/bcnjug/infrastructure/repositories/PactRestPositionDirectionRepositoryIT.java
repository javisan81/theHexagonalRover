package org.bcnjug.infrastructure.repositories;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.RoverNotInitializedException;
import org.bcnjug.infrastructure.repositories.rest.RestPositionDirectoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.bcnjug.domain.Direction.North;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "hexagonalRoverPosition")
class PactRestPositionDirectionRepositoryIT {

    @Pact(provider = "hexagonalRoverPosition", consumer = "restPositionDirectionRepository")
    RequestResponsePact roverNotInitialized(PactDslWithProvider builder) {
        return builder
                .given("Initial State")
                .uponReceiving("A get without state")
                .path("/position")
                .method("GET")
                .willRespondWith()
                .status(404)
                .toPact();
    }

    @Pact(provider = "hexagonalRoverPosition", consumer = "restPositionDirectionRepository")
    RequestResponsePact roverInitializedIn1_1_North(PactDslWithProvider builder) {
        return builder
                .given("Rover initialized in 1,1 North")
                .uponReceiving("A get")
                .path("/position")
                .method("GET")
                .willRespondWith()
                .body("""
                        {
                            "x": 1,
                            "y": 1,
                            "direction": "North"
                        }
                        """)
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .toPact();
    }


    @Pact(provider = "hexagonalRoverPosition", consumer = "restPositionDirectionRepository")
    RequestResponsePact savePosition(PactDslWithProvider builder) {
        return builder
                .given("Rover in any position")
                .uponReceiving("A post")
                .path("/position")
                .method("POST")
                .body("""
                        {
                            "x": 1,
                            "y": 1,
                            "direction": "North"
                        }
                        """)
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "roverNotInitialized")
    void roverWithNoPositionDirection(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        RestPositionDirectoryRepository restPositionDirectoryRepository = new RestPositionDirectoryRepository(restTemplate);

        assertThrows(RoverNotInitializedException.class, restPositionDirectoryRepository::get);
    }

    @Test
    @PactTestFor(pactMethod = "roverInitializedIn1_1_North")
    void roverInitializedIn1_1_North(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        RestPositionDirectoryRepository restPositionDirectoryRepository = new RestPositionDirectoryRepository(restTemplate);

        assertEquals(new PositionDirection(new Position(1, 1), North), restPositionDirectoryRepository.get());
    }


    @Test
    @PactTestFor(pactMethod = "savePosition")
    void savePositionInRover(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        RestPositionDirectoryRepository restPositionDirectoryRepository = new RestPositionDirectoryRepository(restTemplate);

        restPositionDirectoryRepository.save(new PositionDirection(new Position(1, 1), North));
    }

}
