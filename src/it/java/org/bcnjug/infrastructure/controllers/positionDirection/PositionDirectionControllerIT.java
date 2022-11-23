package org.bcnjug.infrastructure.controllers.positionDirection;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.bcnjug.domain.Direction;
import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.RoverNotInitializedException;
import org.bcnjug.infrastructure.repositories.JPAPositionDirectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Provider("hexagonalRoverPosition")
@PactFolder("pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PositionDirectionControllerIT {

    @LocalServerPort
    int localServerPort;
    @MockBean
    private JPAPositionDirectionRepository jpaPositionDirectionRepository;

    @State("Initial State")
    public void initialState(){
        when(jpaPositionDirectionRepository.get()).thenThrow(new RoverNotInitializedException());
    }

    @State("Rover initialized in 1,1 North")
    public void roverInitializedIn1_1_North(){
        when(jpaPositionDirectionRepository.get()).thenReturn(new PositionDirection(new Position(1,1), Direction.North));
    }

    @State("Rover in any position")
    public void roverInAnyPosition(){
    }

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", localServerPort, "/"));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }


}