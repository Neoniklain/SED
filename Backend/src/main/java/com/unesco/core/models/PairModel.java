package com.unesco.core.models;

public class PairModel {
    private int pairnumber;
    private String weektype;
    private String dayofweek;
    private String professor;
    private String room;
    private String discipline;
    private String group;
    private String department;

    public PairModel(int pairnumber, String weektype,
                     String dayofweek, String professor, String room, String discipline, String group, String department) {
        this.pairnumber = pairnumber;
        this.weektype = weektype;
        this.dayofweek = dayofweek;
        this.professor = professor;
        this.room = room;
        this.discipline = discipline;
        this.group = group;
        this.department = department;
    }

    public PairModel(int pairnumber, String weektype,
                     String dayofweek, String professor, String room, String discipline, String group) {
        this.pairnumber = pairnumber;
        this.weektype = weektype;
        this.dayofweek = dayofweek;
        this.professor = professor;
        this.room = room;
        this.discipline = discipline;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
