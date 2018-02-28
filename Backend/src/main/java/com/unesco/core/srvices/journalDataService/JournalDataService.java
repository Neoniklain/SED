package com.unesco.core.srvices.journalDataService;

import com.unesco.core.models.journal.Journal;
import com.unesco.core.models.journal.JournalCell;
import com.unesco.core.models.journal.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalDataService implements IJournalDataService
{
   private Journal journal;

   public JournalDataService() {
      List<Student> students = new ArrayList<>();
      int[] days = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
      students.add(new Student("Василий Пупкин"));
      students.add(new Student("Пуп Васильевич"));
      students.add(new Student("Пупилий Василькин"));
      students.add(new Student("Артем Савов"));
      students.add(new Student("Сав Мартемов"));
      journal = new Journal(students, days);
   }

   public Journal getJournal() {
      return journal;
   }

   public List<JournalCell> getCellsForDate(int date)
   {
      List<JournalCell> result = new ArrayList<>();
      for (JournalCell cell : journal.getJournalCell())
      {
         if(cell.getDate()==date)
            result.add(cell);
      }
      return result;
   }

   public List<JournalCell> getCellsForStudent(Student student)
   {
      List<JournalCell> result = new ArrayList<>();
      for (JournalCell cell : journal.getJournalCell())
      {
         if(cell.getStudent().getName()==student.getName())
            result.add(cell);
      }
      return result;
   }

}
