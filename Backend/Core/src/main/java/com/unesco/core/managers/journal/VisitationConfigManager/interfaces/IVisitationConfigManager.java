package com.unesco.core.managers.journal.VisitationConfigManager.interfaces;

import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.managers.IValidateManager;

import java.util.Date;
import java.util.List;


public interface IVisitationConfigManager extends IValidateManager {

    void init(VisitationConfigDTO config);
    List<Date> GetDates();
    VisitationConfigDTO get();
    void SetDeafaultConfig();
    void SetLesson(LessonDTO lesson);
}
