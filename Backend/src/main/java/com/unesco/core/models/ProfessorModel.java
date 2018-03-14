package com.unesco.core.models;

import com.unesco.core.entities.Discipline;

import java.util.List;

public class ProfessorModel {
    public long id;

    public String fio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public ProfessorModel(String fio) {
        this.fio = fio;
    }
}
