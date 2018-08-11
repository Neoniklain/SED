package com.unesco.core.services.dataService.journal.visitation;

import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.services.dataService.IDataService;


public interface IVisitationConfigDataService extends IDataService<VisitationConfigDTO> {
    VisitationConfigDTO getByLesson(long lessonId);
}

