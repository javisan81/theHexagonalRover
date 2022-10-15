package org.bcnjug.domain;

import java.util.HashMap;
import java.util.Map;

import static org.bcnjug.domain.Direction.*;

public class MovementFactory {
    private static final Movement forwardMovement = new Movement(new HashMap<>() {
        {
            this.put(North, new Move(new Move.Vector(0, 1), North));
            this.put(South, new Move(new Move.Vector(0, -1), South));
            this.put(East, new Move(new Move.Vector(1, 0), East));
            this.put(West, new Move(new Move.Vector(-1, 0), West));
        }
    });

    private static final Movement backwardMovement = new Movement(new HashMap<>() {
        {
            this.put(North, new Move(new Move.Vector(0, -1), North));
            this.put(South, new Move(new Move.Vector(0, 1), South));
            this.put(East, new Move(new Move.Vector(-1, 0), East));
            this.put(West, new Move(new Move.Vector(1, 0), West));
        }
    });
    private static final Movement leftMovement = new Movement(new HashMap<>() {
        {
            this.put(North, new Move(new Move.Vector(0, 0), West));
            this.put(South, new Move(new Move.Vector(0, 0), East));
            this.put(East, new Move(new Move.Vector(0, 0), North));
            this.put(West, new Move(new Move.Vector(0, 0), South));
        }
    });
    private static final Movement rightMovement = new Movement(new HashMap<>() {
        {
            this.put(North, new Move(new Move.Vector(0, 0), East));
            this.put(South, new Move(new Move.Vector(0, 0), West));
            this.put(East, new Move(new Move.Vector(0, 0), South));
            this.put(West, new Move(new Move.Vector(0, 0), North));
        }
    });
    private static final Map<MoveCommand, Movement> movementMap = new HashMap<>() {
        {
            this.put(MoveCommand.Forward, forwardMovement);
            this.put(MoveCommand.Backward, backwardMovement);
            this.put(MoveCommand.Right, rightMovement);
            this.put(MoveCommand.Left, leftMovement);
        }
    };

    public static Movement create(MoveCommand move) {
        return movementMap.get(move);
    }
}