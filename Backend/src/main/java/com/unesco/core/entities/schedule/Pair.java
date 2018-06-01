package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name = "un_pair")
public class Pair {
    @Id
    @SequenceGenerator(name = "pairSequenceGen", sequenceName = "pairSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pairSequenceGen")
    private long id;
    private int pairNumber;
    private String dayofweek;
    private String weektype;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    public Pair() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getPairNumber() {
        return pairNumber;
    }
    public void setPairNumber(int pairNumber) {
        this.pairNumber = pairNumber;
    }

    public String getDayofweek() {
        return dayofweek;
    }
    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getWeektype() {
        return weektype;
    }
    public void setWeektype(String weektype) {
        this.weektype = weektype;
    }

    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    public Lesson getLesson() {
        return lesson;
    }
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

}
