package com.unesco.core.srvices.journalDataService;

import com.unesco.core.models.journal.Journal;
import com.unesco.core.models.journal.JournalCell;
import com.unesco.core.models.journal.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IJournalDataService {
   Journal getJournal();
   List<JournalCell> getCellsForDate(int date);
   List<JournalCell> getCellsForStudent(Student student);
}
