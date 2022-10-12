package org.bcnjug.domain;

import org.bcnjug.infrastructure.Position;
import org.bcnjug.infrastructure.PositionDirection;

public interface MarsRoverUseCase {
    void setPosition(PositionDirection positionDirection);
    Position getPosition();
    Direction getDirection();
}
