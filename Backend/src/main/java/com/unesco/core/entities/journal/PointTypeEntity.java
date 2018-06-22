package com.unesco.core.entities.journal;

import javax.persistence.*;

@Entity
@Table(name="un_pointType")
public class PointTypeEntity {
    @Id
    @SequenceGenerator(name = "pointTypeSequenceGen", sequenceName = "pointTypeSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointTypeSequenceGen")
    private long id;
    private String name;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}