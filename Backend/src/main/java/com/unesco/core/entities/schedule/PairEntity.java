package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name = "un_pair")
public class PairEntity {
    @Id
    @SequenceGenerator(name = "pairSequenceGen", sequenceName = "pairSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pairSequenceGen")
    private long id;
    private int pairNumber;
    private String dayofweek;
    private String weektype;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomEntity room;
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lesson;

    public PairEntity() {
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

    public RoomEntity getRoomEntity() {
        return room;
    }
    public void setRoomEntity(RoomEntity room) {
        this.room = room;
    }

    public LessonEntity getLessonEntity() {
        return lesson;
    }
    public void setLessonEntity(LessonEntity lesson) {
        this.lesson = lesson;
    }

}
