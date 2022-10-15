package org.bcnjug.domain;

public class Move {
    private final Vector vector;
    private final Direction direction;

    public Move(Vector vector, Direction direction) {
        this.vector = vector;
        this.direction = direction;
    }

    public PositionDirection apply(Position position) {
        return new PositionDirection(position.applyVector(this.vector), direction);
    }
}
