package org.bcnjug.infrastructure;

import lombok.Getter;

@Getter
public class Direction{
    private final String direction;

    public Direction(org.bcnjug.domain.Direction direction) {
        switch (direction){
            case South -> this.direction="S";
            case North -> this.direction="N";
            default -> this.direction="N";
        }
    }
}
