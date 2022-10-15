package org.bcnjug.domain;

import java.util.List;

public class MarsRover implements MarsRoverUseCase {
    private PositionDirection positionDirection;

    @Override
    public void setPosition(PositionDirection positionDirection) {
        this.positionDirection = positionDirection;
    }

    @Override
    public Position getPosition() {
        return positionDirection.position();
    }

    @Override
    public Direction getDirection() {
        return positionDirection.direction();
    }

    @Override
    public void move(List<MoveCommand> commands) {
        this.positionDirection = positionDirection.move(commands);
    }
}
