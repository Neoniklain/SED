package com.unesco.core.ViewModel;

import com.unesco.core.entities.Discipline;

import java.util.List;

public class ProfessorViewModel {
    public String fio;
    public List<Discipline> disciplines;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public ProfessorViewModel(String fio, List<Discipline> disciplines) {
        this.fio = fio;
        this.disciplines = disciplines;
    }
}
