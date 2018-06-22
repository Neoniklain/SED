package com.unesco.core.managers.journal.journalManager.interfaces.journal;

import com.unesco.core.managers.IValidateManager;
import com.unesco.core.models.journal.JournalDTO;
import com.unesco.core.models.journal.LessonEventDTO;

import java.util.Date;
import java.util.List;


public interface IJournalManager extends IValidateManager {

    void Init(JournalDTO journal);
    void InitEmptyCells(List<LessonEventDTO> lessonEvents);
    List<Date> GetDates();

    JournalDTO Get();
}
