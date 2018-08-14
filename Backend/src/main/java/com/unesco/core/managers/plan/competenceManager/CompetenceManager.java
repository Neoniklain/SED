package com.unesco.core.managers.plan.competenceManager;


import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.managers.plan.competenceManager.interfaces.competence.ICompetenceManager;
import com.unesco.core.services.dataService.plan.competenceService.ICompetenceDataService;
import org.springframework.beans.factory.annotation.Autowired;

public class CompetenceManager implements ICompetenceManager {

    @Autowired
    public ICompetenceDataService dataService;

    public CompetenceEntity competenceEntity;

    public CompetenceManager() {
        competenceEntity = new CompetenceEntity();
    }

    public void init(CompetenceEntity Professor) {
        competenceEntity = Professor;
    }

    public CompetenceEntity get() {
        return competenceEntity;
    }

    @Override
    public ResponseStatusDTO validate() {
        ResponseStatusDTO result = new ResponseStatusDTO(StatusTypes.OK);
        return result;
    }
}
