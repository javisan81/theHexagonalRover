package org.bcnjug.domain;

import java.util.Map;

public class Movement {
    private final Map<Direction, Move> movesByDirection;

    public Movement(Map<Direction, Move> movesByDirection) {
        this.movesByDirection = movesByDirection;
    }

    public PositionDirection apply(Position position, Direction direction) {
        return this.movesByDirection.get(direction).apply(position);
    }
}
