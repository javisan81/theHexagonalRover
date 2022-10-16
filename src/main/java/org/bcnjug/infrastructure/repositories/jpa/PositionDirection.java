package org.bcnjug.infrastructure.repositories.jpa;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positionDirection")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PositionDirection {
    @Id
    @Column(name = "name")
    private String name;

    private int x;
    private int y;
    private String coordinate;
}

