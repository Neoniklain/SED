package com.unesco.core.services.dataService.plan.competenceService;

import com.unesco.core.entities.plan.CompetenceEntity;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface ICompetenceDataService extends IDataService<CompetenceEntity> {
    List<CompetenceEntity> getPage(FilterQueryDTO filter);
}
