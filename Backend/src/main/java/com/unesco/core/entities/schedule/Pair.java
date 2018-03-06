package com.unesco.core.entities.schedule;

import com.unesco.core.entities.*;

import javax.persistence.*;

@Entity
@Table(name = "pair")
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "pairnumber")
    private int pairNumber;

    //овердохера связей
    @ManyToOne
    @JoinColumn(name = "dayofweek_id", referencedColumnName = "id")
    private DayOfWeek dayofweek;

    @ManyToOne
    @JoinColumn(name = "weektype_id", referencedColumnName = "id")
    private WeekType weektype;

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

    public Pair(int pairNumber, DayOfWeek dayofweek,
                WeekType weektype, Discipline discipline, Room room, Professor professor, Group group) {
        this.pairNumber = pairNumber;
        this.dayofweek = dayofweek;
        this.weektype = weektype;
        this.discipline = discipline;
        this.room = room;
        this.professor = professor;
        this.group = group;
    }

    public Pair() {
    }

    public int getId() {
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

    public DayOfWeek getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(DayOfWeek dayofweek) {
        this.dayofweek = dayofweek;
    }

    public WeekType getWeektype() {
        return weektype;
    }

    public void setWeektype(WeekType weektype) {
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
