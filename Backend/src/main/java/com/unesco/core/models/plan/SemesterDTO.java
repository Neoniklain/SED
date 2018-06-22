package com.unesco.core.models.plan;

public class SemesterDTO {
    private long id;
    private int number;
    private String control_type;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
