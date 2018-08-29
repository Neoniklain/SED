package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name = "un_pair")
public class PairEntity {
    @Id
    @SequenceGenerator(name = "pairSequenceGen", sequenceName = "pairSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pairSequenceGen")
    private long id;
    /**
     * Номер занятия
     */
    private int pairNumber;
    /**
     * День недели
     */
    private String dayofweek;
    /**
     * Тип недели
     * Чет, Нечет, Все
     */
    private String weektype;
    /**
     * Занятие по выбору
     */
    private boolean optionally;
    /**
     * Потоковое занятие
     */
    private boolean flow;
    /**
    * Номер подгруппы
    */
    private int subgroup;
    @ManyToOne
    @JoinColumn(name = "pair_type_id", referencedColumnName = "id")
    private PairTypeEntity pairType;
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

    public boolean isOptionally() {
        return optionally;
    }
    public void setOptionally(boolean optionally) {
        this.optionally = optionally;
    }

    public PairTypeEntity getPairType() {
        return pairType;
    }
    public void setPairType(PairTypeEntity pairType) {
        this.pairType = pairType;
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

    public RoomEntity getRoom() {
        return room;
    }
    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public LessonEntity getLesson() {
        return lesson;
    }
    public void setLesson(LessonEntity lesson) {
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
