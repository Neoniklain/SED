package com.unesco.core.managers.journal.journalManager.interfaces.journal;

import com.unesco.core.dto.journal.JournalDTO;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.journal.VisitationConfigDTO;
import com.unesco.core.managers.IValidateManager;

import java.util.Date;
import java.util.List;


public interface IJournalManager extends IValidateManager {

    void Init(JournalDTO journal, List<LessonEventDTO> lessonEvents, VisitationConfigDTO visitConfig);
    void CreateJournal();
    List<Date> GetDates();

    JournalDTO Get();
}
