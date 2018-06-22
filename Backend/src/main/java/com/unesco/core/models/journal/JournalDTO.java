package com.unesco.core.models.journal;

import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.shedule.LessonDTO;
import com.unesco.core.models.shedule.PairDTO;

import java.util.Date;
import java.util.List;

public class JournalDTO
{
   private LessonDTO lesson;
   private List<PairDTO> pairs;
   private List<StudentDTO> students;
   private List<Date> dates;
   private List<PointDTO> journalCell;

   public JournalDTO()
   {
   }

   public LessonDTO getLesson() {
      return lesson;
   }
   public void setLesson(LessonDTO lesson) {
      this.lesson = lesson;
   }

   public List<StudentDTO> getStudents() {
      return students;
   }
   public void setStudents(List<StudentDTO> students) {
      this.students = students;
   }

   public List<Date> getDates() {
      return dates;
   }
   public void setDates(List<Date> dates) {
      this.dates = dates;
   }

   public List<PointDTO> getJournalCell() {
      return journalCell;
   }
   public void setJournalCell(List<PointDTO> journalCell) {
      this.journalCell = journalCell;
   }

   public List<PairDTO> getPairs() {
      return pairs;
   }
   public void setPairs(List<PairDTO> pairs) {
      this.pairs = pairs;
   }

}
