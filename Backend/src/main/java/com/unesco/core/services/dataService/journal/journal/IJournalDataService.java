package com.unesco.core.services.dataService.journal.journal;

import com.unesco.core.dto.journal.JournalDTO;

public interface IJournalDataService {
    JournalDTO get(long lessonId);

    JournalDTO save(JournalDTO journal);
}
