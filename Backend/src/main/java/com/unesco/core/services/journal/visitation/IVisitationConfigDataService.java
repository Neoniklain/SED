package com.unesco.core.services.journal.visitation;

import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.services.IDataService;


public interface IVisitationConfigDataService extends IDataService<VisitationConfigDTO> {
    VisitationConfigDTO GetByLesson(long lessonId);
}

