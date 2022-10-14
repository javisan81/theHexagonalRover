package org.bcnjug.infrastructure;

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
        };
    }

    public String getDirection() {
        return direction;
    }
}
