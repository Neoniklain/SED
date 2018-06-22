package com.unesco.core.models.journal;

import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.shedule.PairDTO;

import java.util.Date;

public class PointDTO {

    private long id;
    private int value;
    private StudentDTO student;
    private Date date;
    private PointTypeDTO type;
    private PairDTO pair;

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

    public StudentDTO getStudent() {
        return student;
    }
    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public PointTypeDTO getType() {
        return type;
    }
    public void setType(PointTypeDTO type) {
        this.type = type;
    }

    public PairDTO getPair() {
        return pair;
    }
    public void setPair(PairDTO pair) {
        this.pair = pair;
    }


}
