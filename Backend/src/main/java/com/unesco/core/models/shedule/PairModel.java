package com.unesco.core.models.shedule;

public class PairModel {

    private long id;
    private int pairNumber;
    private String dayofweek;
    private String weektype;
    private RoomModel room;
    private LessonModel lesson;

    public PairModel() {
        this.lesson = new LessonModel();
        this.room = new RoomModel();
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

    public RoomModel getRoom() {
        return room;
    }
    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public LessonModel getLesson() {
        return lesson;
    }
    public void setLesson(LessonModel lesson) {
        this.lesson = lesson;
    }

}
