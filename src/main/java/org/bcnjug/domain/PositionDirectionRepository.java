package org.bcnjug.domain;

public interface PositionDirectionRepository {
    void save(PositionDirection positionDirection);
    PositionDirection get();
}
