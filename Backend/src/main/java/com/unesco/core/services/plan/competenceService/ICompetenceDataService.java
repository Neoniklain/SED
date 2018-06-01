package com.unesco.core.services.plan.competenceService;

import com.unesco.core.entities.plan.Competence;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface ICompetenceDataService extends IDataService<Competence> {
    List<Competence> GetPage(FilterQuery filter);
}
