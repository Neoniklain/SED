package com.unesco.core.services.schedule.fieldOfKnowledgeService;

import com.unesco.core.models.shedule.FieldOfKnowledgeModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IFieldOfKnowledgeDataService extends IDataService<FieldOfKnowledgeModel> {
    List<FieldOfKnowledgeModel> GetPage(FilterQuery filter);
}
