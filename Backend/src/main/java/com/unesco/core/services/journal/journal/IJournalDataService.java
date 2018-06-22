package com.unesco.core.services.journal.journal;

import com.unesco.core.models.journal.JournalDTO;

public interface IJournalDataService {
    JournalDTO Get(long lessonId);

    JournalDTO Save(JournalDTO journal);
}
