package com.unesco.core.models.shedule;

import com.unesco.core.models.account.ProfessorDTO;

import java.util.List;

public class SheduleProfessorLineDTO {

    private ProfessorDTO professor;
    private List<PairDTO> pairs;

    public ProfessorDTO getProfessor() {
        return professor;
    }
    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public List<PairDTO> getPairs() {
        return pairs;
    }
    public void setPairs(List<PairDTO> pairs) {
        this.pairs = pairs;
    }

    public SheduleProfessorLineDTO() {
    }
}
