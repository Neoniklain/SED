package com.unesco.core.services.dataService.journal.journal;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.JournalDTO;

public interface IJournalDataService {
    JournalDTO get(long lessonId);

    ResponseStatusDTO<JournalDTO> save(JournalDTO journal);
}
