package org.bcnjug.domain;

public record Position(int x, int y) {
    public Position applyVector(Vector vector){
        return vector.apply(this.x, this.y);
    }
}
