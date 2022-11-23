package org.bcnjug.infrastructure.controllers.positionDirection;

import org.bcnjug.domain.Direction;
import org.bcnjug.domain.Position;
import org.bcnjug.domain.PositionDirection;
import org.bcnjug.domain.RoverNotInitializedException;
import org.bcnjug.infrastructure.repositories.JPAPositionDirectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("repository/position")
public class PositionDirectionController {
    public static class PositionDirectionRest{
        public int x;
        public int y;
        public String direction;

        public PositionDirectionRest(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public PositionDirectionRest() {
        }
    }

    private final JPAPositionDirectionRepository jpaPositionDirectionRepository;

    public PositionDirectionController(JPAPositionDirectionRepository jpaPositionDirectionRepository) {
        this.jpaPositionDirectionRepository = jpaPositionDirectionRepository;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Void> save(@RequestBody PositionDirectionRest positionDirection){
        jpaPositionDirectionRepository.save(new PositionDirection(new Position(positionDirection.x, positionDirection.y), Direction.valueOf(positionDirection.direction)));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }
    @GetMapping
    public ResponseEntity<PositionDirectionRest> get(){
        PositionDirection positionDirection = jpaPositionDirectionRepository.get();
        PositionDirectionRest positionDirectionRest = new PositionDirectionRest(positionDirection.position().x(), positionDirection.position().y(), positionDirection.direction().toString());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(positionDirectionRest);
    }

    @ExceptionHandler({ RoverNotInitializedException.class })
    public ResponseEntity<Void> handleException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
