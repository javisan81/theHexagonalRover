package org.bcnjug.infrastructure.controllers;

import org.bcnjug.domain.Direction;

public class JsonDirection {
    private final String direction;

    public JsonDirection(Direction direction) {
        this.direction = toValue(direction);
    }

    private String toValue(Direction direction) {
        return switch (direction) {
            case North -> "N";
            case South -> "S";
            case East -> "E";
            case West -> "W";
        };
    }

    public String getDirection() {
        return direction;
    }
}
