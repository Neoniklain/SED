package com.unesco.core.managers.plan.competenceManager;


import com.unesco.core.entities.plan.Competence;
import com.unesco.core.managers.plan.competenceManager.interfaces.competenceList.ICompetenceListManager;

import java.util.ArrayList;
import java.util.List;

public class CompetenceListManager implements ICompetenceListManager {

    public List<Competence> competenceList;

    public CompetenceListManager() {
        competenceList = new ArrayList<>();
    }

    public void Init(List<Competence> Competenceist) {
        competenceList = Competenceist;
    }

    public List<Competence> GetAll() {
        return competenceList;
    }

}
