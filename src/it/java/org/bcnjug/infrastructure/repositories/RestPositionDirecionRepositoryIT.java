package org.bcnjug.infrastructure.repositories;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.bcnjug.domain.RoverNotInitializedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

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


    @Test
    @PactTestFor
    void roverWithNoPositionDirection(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        RestPositionDirectoryRepository restPositionDirectoryRepository = new RestPositionDirectoryRepository(restTemplate);

        assertThrows(RoverNotInitializedException.class, restPositionDirectoryRepository::get);
    }

}
