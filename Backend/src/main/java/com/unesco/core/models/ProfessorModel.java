package com.unesco.core.models;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.Professor;
import com.unesco.core.models.additional.EntityModel;

import java.util.ArrayList;
import java.util.List;

public class ProfessorModel {
    public String fio;
    public List<Discipline> disciplines;

    public ProfessorModel() {
        this.fio = "";
        this.disciplines = new ArrayList();
    }
    public ProfessorModel(String fio, List<Discipline> disciplines) {
        this.fio = fio;
        this.disciplines = disciplines;
    }

    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
}
