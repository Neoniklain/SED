package com.unesco.core.entities.schedule;

import com.unesco.core.entities.LongId;

import javax.persistence.*;

@Entity
@Table(name = "un_week_type")
public class WeekType implements LongId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
