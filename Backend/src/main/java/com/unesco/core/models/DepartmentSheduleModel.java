package com.unesco.core.models;

import com.unesco.core.entities.schedule.Pair;

import java.util.ArrayList;
import java.util.List;

public class DepartmentSheduleModel {

    private ProfessorModel professor;
    private List<PairModel> pairs;

    public ProfessorModel getProfessor() {
        return professor;
    }
    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public List<PairModel> getPairs() {
        return pairs;
    }
    public void setPairs(List<PairModel> pairs) {
        this.pairs = pairs;
    }

    public DepartmentSheduleModel() {
        this.pairs = new ArrayList();
        this.professor = new ProfessorModel();
    }
}
