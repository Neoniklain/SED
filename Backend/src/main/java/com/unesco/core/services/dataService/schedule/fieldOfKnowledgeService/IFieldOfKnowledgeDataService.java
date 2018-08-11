package com.unesco.core.services.dataService.schedule.fieldOfKnowledgeService;

import com.unesco.core.dto.shedule.FieldOfKnowledgeDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IFieldOfKnowledgeDataService extends IDataService<FieldOfKnowledgeDTO> {
    List<FieldOfKnowledgeDTO> getPage(FilterQueryDTO filter);
    FieldOfKnowledgeDTO getByName(String name);
}
