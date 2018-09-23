package com.unesco.core.services.dataService.schedule.fieldOfKnowledgeService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.shedule.FieldOfKnowledgeDTO;
import com.unesco.core.services.dataService.IDataService;

public interface IFieldOfKnowledgeDataService extends IDataService<FieldOfKnowledgeDTO> {
    PageResultDTO<FieldOfKnowledgeDTO> getPage(FilterQueryDTO filter);
    FieldOfKnowledgeDTO getByName(String name);
}
