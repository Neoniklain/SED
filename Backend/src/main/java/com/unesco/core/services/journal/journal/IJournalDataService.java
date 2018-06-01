package com.unesco.core.services.journal.journal;

import com.unesco.core.models.journal.JournalModel;

public interface IJournalDataService {
    JournalModel Get(long professorId,
                        long groupId,
                        long disciplineId);

    JournalModel Save(JournalModel journal);
}
