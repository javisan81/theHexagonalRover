package org.bcnjug.domain;

public class Move {
    public static class Vector {
        private final int x;
        private final int y;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position apply(Position position) {
            return new Position(position.x() + this.x, position.y() + this.y);
        }
    }

    private final Vector vector;
    private final Direction direction;

    public Move(Vector vector, Direction direction) {
        this.vector = vector;
        this.direction = direction;
    }

    public PositionDirection apply(Position position) {
        return new PositionDirection(this.vector.apply(position), direction);
    }
}
