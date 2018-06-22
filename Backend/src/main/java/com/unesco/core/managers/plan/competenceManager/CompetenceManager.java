package com.unesco.core.managers.plan.competenceManager;


import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.managers.plan.competenceManager.interfaces.competence.ICompetenceManager;
import com.unesco.core.services.plan.competenceService.ICompetenceDataService;
import org.springframework.beans.factory.annotation.Autowired;

public class CompetenceManager implements ICompetenceManager {

    @Autowired
    public ICompetenceDataService dataService;

    public CompetenceEntity competenceEntity;

    public CompetenceManager() {
        competenceEntity = new CompetenceEntity();
    }

    public void Init(CompetenceEntity Professor) {
        competenceEntity = Professor;
    }

    public CompetenceEntity Get() {
        return competenceEntity;
    }
}
