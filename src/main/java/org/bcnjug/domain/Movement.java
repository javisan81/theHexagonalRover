package org.bcnjug.domain;

import java.util.Map;

public class Movement {
    public static class Vector{
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
    private final Map<Direction, Vector> vectorByDirection;

    public Movement(Map<Direction, Vector> vectorByDirection) {
        this.vectorByDirection = vectorByDirection;
    }

    public PositionDirection apply(PositionDirection positionDirection) {
        return new PositionDirection(this.vectorByDirection.get(positionDirection.direction()).apply(positionDirection.position()), positionDirection.direction());
    }
}
