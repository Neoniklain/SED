package com.unesco.core.dto.journal;

import java.util.Date;

public class PointDTO {

    private long id;
    private int value;
    private long studentId;
    private Date date;
    private PointTypeDTO type;
    private long pairId;

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

    public long getStudentId() {
        return studentId;
    }
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getPairId() {
        return pairId;
    }
    public void setPairId(long pairId) {
        this.pairId = pairId;
    }
}
