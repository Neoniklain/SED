package com.unesco.core.models.shedule;

import com.unesco.core.models.account.ProfessorModel;

import java.util.List;

public class SheduleProfessorLineModel {

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

    public SheduleProfessorLineModel() {
    }
}
