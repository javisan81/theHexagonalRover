package org.bcnjug.domain;

public interface MarsRoverUseCase {
    void setPosition(PositionDirection positionDirection);
    Position getPosition();
    Direction getDirection();
}
