package com.unesco.core.models;

import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.models.additional.EntityModel;

public class PairModel {
    private int pairnumber;
    private String weektype;
    private String dayofweek;
    private String professor;
    private String room;
    private String discipline;
    private GroupModel group;
    private String department;

    public PairModel() {
        this.pairnumber = 0;
        this.weektype = "";
        this.dayofweek = "";
        this.professor = "";
        this.room = "";
        this.discipline = "";
        this.group = new GroupModel();
        this.department = "";
    }


    public int getPairnumber() {
        return pairnumber;
    }

    public void setPairnumber(int pairnumber) {
        this.pairnumber = pairnumber;
    }

    public String getWeektype() {
        return weektype;
    }

    public void setWeektype(String weektype) {
        this.weektype = weektype;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
