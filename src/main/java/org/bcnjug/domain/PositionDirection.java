package org.bcnjug.domain;

import java.util.List;

public record PositionDirection(Position position, Direction direction) {
    public PositionDirection move(List<MoveCommand> commands) {
        PositionDirection finalPosition = this;
        for (MoveCommand moveCommand : commands) {
            finalPosition = MovementFactory.create(moveCommand).apply(finalPosition.position, finalPosition.direction);
        }
        return finalPosition;
    }
}
