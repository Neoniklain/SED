package com.unesco.core.managers.plan.competenceManager;


import com.unesco.core.entities.plan.Competence;
import com.unesco.core.managers.plan.competenceManager.interfaces.competence.ICompetenceManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.services.plan.competenceService.ICompetenceDataService;
import org.springframework.beans.factory.annotation.Autowired;

public class CompetenceManager implements ICompetenceManager {

    @Autowired
    public ICompetenceDataService dataService;

    public Competence competence;

    public CompetenceManager() {
        competence = new Competence();
    }

    public void Init(Competence Professor) {
        competence = Professor;
    }

    public Competence Get() {
        return competence;
    }
}
