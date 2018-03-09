package com.unesco.core.models;

import com.unesco.core.entities.Discipline;

import java.util.List;

public class ProfessorModel {
    public String fio;

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
