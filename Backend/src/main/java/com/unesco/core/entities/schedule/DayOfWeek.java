package com.unesco.core.entities.schedule;

import com.unesco.core.entities.LongId;

import javax.persistence.*;

@Entity
@Table(name = "un_dayofweek")
public class DayOfWeek implements LongId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "dayofweek")
    private String dayofweek;

    public long getId() {
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
