package org.bcnjug.domain;

import java.util.List;

public class MarsRover implements MarsRoverUseCase {
    private final PositionDirectionRepository positionDirectionRepository;

    public MarsRover(PositionDirectionRepository positionDirectionRepository) {
        this.positionDirectionRepository = positionDirectionRepository;
    }

    @Override
    public void setPosition(PositionDirection positionDirection) {
        this.positionDirectionRepository.save(positionDirection);
    }

    @Override
    public Position getPosition() {
        PositionDirection positionDirection = getRoverPosition();
        return positionDirection.position();
    }

    @Override
    public Direction getDirection() {
        PositionDirection positionDirection = getRoverPosition();
        return positionDirection.direction();
    }

    @Override
    public void move(List<MoveCommand> commands) {
        PositionDirection positionDirection = getRoverPosition();
        this.positionDirectionRepository.save(positionDirection.move(commands));
    }

    private PositionDirection getRoverPosition() {
        PositionDirection positionDirection = this.positionDirectionRepository.get();
        if(positionDirection == null){
            throw new RoverNotInitializedException();
        }
        return positionDirection;
    }
}
