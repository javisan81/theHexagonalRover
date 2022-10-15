package org.bcnjug.domain;

public  class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position apply(int x, int y) {
        return new Position(x + this.x, y + this.y);
    }
}
