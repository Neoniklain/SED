package com.unesco.core.services.plan.competenceService;

import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ICompetenceDataService extends IDataService<CompetenceEntity> {
    List<CompetenceEntity> GetPage(FilterQueryDTO filter);
}
