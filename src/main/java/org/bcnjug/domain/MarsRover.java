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
        return this.positionDirectionRepository.get().position();
    }

    @Override
    public Direction getDirection() {
        return this.positionDirectionRepository.get().direction();
    }

    @Override
    public void move(List<MoveCommand> commands) {
        PositionDirection positionDirection1 = this.positionDirectionRepository.get();
        this.positionDirectionRepository.save(positionDirection1.move(commands));
    }
}
