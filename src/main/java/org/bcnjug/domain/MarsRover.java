package org.bcnjug.domain;

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
}
