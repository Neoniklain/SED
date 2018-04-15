package com.unesco.core.entities.schedule;

import com.unesco.core.entities.*;

import javax.persistence.*;

@Entity
@Table(name = "un_pair")
public class Pair implements LongId {
    @Id
    @SequenceGenerator(name = "pairSequenceGen", sequenceName = "pairSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pairSequenceGen")
    private long id;

    private int pairNumber;

    private String dayofweek;

    private String weektype;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Pair() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
