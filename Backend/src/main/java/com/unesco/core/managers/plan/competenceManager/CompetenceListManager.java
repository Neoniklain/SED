package com.unesco.core.managers.plan.competenceManager;


import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.managers.plan.competenceManager.interfaces.competenceList.ICompetenceListManager;

import java.util.ArrayList;
import java.util.List;

public class CompetenceListManager implements ICompetenceListManager {

    public List<CompetenceEntity> competenceEntityList;

    public CompetenceListManager() {
        competenceEntityList = new ArrayList<>();
    }

    public void Init(List<CompetenceEntity> competenceist) {
        competenceEntityList = competenceist;
    }

    public List<CompetenceEntity> GetAll() {
        return competenceEntityList;
    }

}
