package com.unesco.core.models.journal;

import com.unesco.core.models.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class Journal
{
   private List<StudentModel> students;
   private List<String> dates;
   private List<JournalCell> journalCell;

   public Journal(List<StudentModel> Students, List<String> Dates)
   {
      students = Students;
      dates = Dates;
      journalCell = new ArrayList<>();

      for (StudentModel student : students)
      {
         for (String date : dates)
         {
            journalCell.add(new JournalCell(student, date));
         }
      }
   }

   public List<StudentModel> getStudents() {
      return students;
   }
   public void setStudents(List<StudentModel> students) {
      this.students = students;
   }

   public List<String> getDates() {
      return dates;
   }
   public void setDates(List<String> dates) {
      this.dates = dates;
   }

   public List<JournalCell> getJournalCell() {
      return journalCell;
   }
   public void setJournalCell(List<JournalCell> journalCell) {
      this.journalCell = journalCell;
   }

}
