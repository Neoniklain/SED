package com.unesco.core.services.journal.journal;

import com.unesco.core.models.journal.JournalModel;

public interface IJournalDataService {
    JournalModel Get(long lessonId);

    JournalModel Save(JournalModel journal);
}
