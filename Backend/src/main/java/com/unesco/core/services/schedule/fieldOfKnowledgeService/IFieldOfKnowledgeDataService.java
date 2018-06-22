package com.unesco.core.services.schedule.fieldOfKnowledgeService;

import com.unesco.core.models.shedule.FieldOfKnowledgeDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IFieldOfKnowledgeDataService extends IDataService<FieldOfKnowledgeDTO> {
    List<FieldOfKnowledgeDTO> GetPage(FilterQueryDTO filter);
}
