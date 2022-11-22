package org.bcnjug.infrastructure.repositories.jpa;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positionDirection")
public class PositionDirection {
    @Id
    @Column(name = "name")
    private String name;

    public int x;
    public int y;
    public String coordinate;

    public PositionDirection(String roverName, int x, int y, String coordinate) {
        this.name=roverName;
        this.x=x;
        this.y=y;
        this.coordinate=coordinate;
    }

    public PositionDirection() {

    }
}

