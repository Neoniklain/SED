package com.unesco.core.managers.journal.journalManager.interfaces.journal;

import com.unesco.core.managers.IValidateManager;
import com.unesco.core.models.journal.JournalModel;
import com.unesco.core.models.journal.LessonEventModel;

import java.util.List;


public interface IJournalManager extends IValidateManager {

    void Init(JournalModel journal);
    void InitEmptyCells(List<LessonEventModel> lessonEvents);

    JournalModel Get();
}
