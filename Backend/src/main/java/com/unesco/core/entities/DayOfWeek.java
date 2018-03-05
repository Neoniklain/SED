package com.unesco.core.entities;

import javax.persistence.*;

@Entity
@Table(name = "daysofweek")
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "dayofweek")
    private String dayofweek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }
}
