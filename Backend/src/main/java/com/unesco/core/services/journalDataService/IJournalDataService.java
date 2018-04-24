package com.unesco.core.services.journalDataService;

import com.unesco.core.models.StudentModel;
import com.unesco.core.models.journal.Journal;
import com.unesco.core.models.journal.JournalCell;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IJournalDataService {
   Journal getJournal(int proffesorId, int groupId, int disciplineId);
}
