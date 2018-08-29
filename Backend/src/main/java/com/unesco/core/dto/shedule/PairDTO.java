package com.unesco.core.dto.shedule;

public class PairDTO {

    private long id;
    private int pairNumber;
    private PairTypeDTO pairType;
    private String dayofweek;
    private String weektype;
    private RoomDTO room;
    private LessonDTO lesson;
    private boolean optionally;
    /**
     * Потоковое занятие
     */
    private boolean flow;
    /**
     * Номер подгруппы
     */
    private int subgroup;

    public PairDTO() {
        this.lesson = new LessonDTO();
        this.room = new RoomDTO();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public boolean isOptionally() {
        return optionally;
    }
    public void setOptionally(boolean optionally) {
        this.optionally = optionally;
    }

    public int getPairNumber() {
        return pairNumber;
    }
    public void setPairNumber(int pairNumber) {
        this.pairNumber = pairNumber;
    }

    public PairTypeDTO getPairType() {
        return pairType;
    }
    public void setPairType(PairTypeDTO pairType) {
        this.pairType = pairType;
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

    public RoomDTO getRoom() {
        return room;
    }
    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public LessonDTO getLesson() {
        return lesson;
    }
    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }

    public int getSubgroup() {
        return subgroup;
    }
    public void setSubgroup(int subgroup) {
        this.subgroup = subgroup;
    }

    public boolean isFlow() {
        return flow;
    }
    public void setFlow(boolean flow) {
        this.flow = flow;
    }
}
