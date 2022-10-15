package org.bcnjug.domain;

import java.util.Map;

public class Movement {
    private final Map<Direction, Move> movesByDirection;

    public Movement(Map<Direction, Move> movesByDirection) {
        this.movesByDirection = movesByDirection;
    }

    public PositionDirection apply(PositionDirection positionDirection) {
        return this.movesByDirection.get(positionDirection.direction()).apply(positionDirection);
    }
}
