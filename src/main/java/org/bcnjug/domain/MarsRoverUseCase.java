package org.bcnjug.domain;

import java.util.List;

public interface MarsRoverUseCase {
    void setPosition(PositionDirection positionDirection);

    Position getPosition();

    Direction getDirection();

    void move(List<MoveCommand> commands);
}
