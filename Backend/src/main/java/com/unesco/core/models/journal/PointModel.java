package com.unesco.core.models.journal;

import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.shedule.PairModel;

import java.util.Date;

public class PointModel {

    private long id;
    private int value;
    private StudentModel student;
    private Date date;
    private PointTypeModel type;
    private PairModel pair;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public StudentModel getStudent() {
        return student;
    }
    public void setStudent(StudentModel student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public PointTypeModel getType() {
        return type;
    }
    public void setType(PointTypeModel type) {
        this.type = type;
    }

    public PairModel getPair() {
        return pair;
    }
    public void setPair(PairModel pair) {
        this.pair = pair;
    }


}
