package com.unesco.core.models;

import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.models.additional.EntityModel;

public class PairModel implements EntityModel<Pair> {
    private int pairnumber;
    private String weektype;
    private String dayofweek;
    private String professor;
    private String room;
    private String discipline;
    private String group;
    private String department;

    public void EntityToModel(Pair pair) {
        this.pairnumber = pair.getPairNumber();
        this.weektype = pair.getWeektype().getType();
        this.dayofweek = pair.getDayofweek().getDayofweek();
        this.professor = pair.getProfessor().getFio();
        this.room = pair.getRoom().getRoom();
        this.discipline = pair.getDiscipline().getName();
        this.group = pair.getGroup().getName();
        this.department = "";
    }

    public PairModel() {
        this.pairnumber = 0;
        this.weektype = "";
        this.dayofweek = "";
        this.professor = "";
        this.room = "";
        this.discipline = "";
        this.group = "";
        this.department = "";
    }

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

    public PairModel(int id, int pairnumber, String weektype,
                     String dayofweek, String professor, String room, String discipline, String group) {
        this.id = id;
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
