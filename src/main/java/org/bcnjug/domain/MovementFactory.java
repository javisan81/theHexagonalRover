package org.bcnjug.domain;

import java.util.HashMap;
import java.util.Map;

public class MovementFactory {
    private static Movement forwardMovement =  new Movement(new HashMap<>() {
        {
            this.put(Direction.North, new Movement.Vector(0, 1));
            this.put(Direction.South, new Movement.Vector(0, -1));
            this.put(Direction.East, new Movement.Vector(1, 0));
            this.put(Direction.West, new Movement.Vector(-1, 0));
        }
    });

    private static Movement backwardMovement =  new Movement(new HashMap<>() {
        {
            this.put(Direction.North, new Movement.Vector(0, -1));
            this.put(Direction.South, new Movement.Vector(0, 1));
            this.put(Direction.East, new Movement.Vector(-1, 0));
            this.put(Direction.West, new Movement.Vector(1, 0));
        }
    });
    private static Map<MoveCommand, Movement> movementMap = new HashMap<>() {
        {
            this.put(MoveCommand.Forward, forwardMovement);
            this.put(MoveCommand.Backward, backwardMovement);
        }
    };

    public static Movement create(MoveCommand move) {
        return movementMap.get(move);
    }
}