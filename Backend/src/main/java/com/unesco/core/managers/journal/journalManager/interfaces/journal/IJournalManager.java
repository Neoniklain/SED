package com.unesco.core.managers.journal.journalManager.interfaces.journal;

import com.unesco.core.models.journal.JournalModel;
import com.unesco.core.models.journal.PairEventModel;

import java.util.List;


public interface IJournalManager {

    void Init(JournalModel journal);
    void InitEmptyCells(List<PairEventModel> lessonEvents);

    JournalModel Get();
}
