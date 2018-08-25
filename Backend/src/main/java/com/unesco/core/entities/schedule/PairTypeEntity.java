package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name = "un_pair_type")
public class PairTypeEntity {
    @Id
    @SequenceGenerator(name = "pairTypeSequenceGen", sequenceName = "pairTypeSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pairTypeSequenceGen")
    private long id;
    @Column(unique=true)
    private String type;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
