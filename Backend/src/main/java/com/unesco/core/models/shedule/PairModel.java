package com.unesco.core.models.shedule;

import com.unesco.core.models.account.ProfessorModel;

public class PairModel {

    private long id;
    private int pairNumber;
    private String dayofweek;
    private String weektype;
    private DisciplineModel discipline;
    private RoomModel room;
    private ProfessorModel professor;
    private GroupModel group;

    public PairModel() {
        this.discipline = new DisciplineModel();
        this.room = new RoomModel();
        this.professor = new ProfessorModel();
        this.group = new GroupModel();
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

    public DisciplineModel getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineModel discipline) {
        this.discipline = discipline;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

}
