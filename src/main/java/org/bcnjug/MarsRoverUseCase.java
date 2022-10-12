package org.bcnjug;

public interface MarsRoverUseCase {
    void setPosition(PositionDirection positionDirection);
    Position getPosition();
    Direction getDirection();
}
