package com.unesco.core.models.journal;

import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.shedule.LessonModel;
import com.unesco.core.models.shedule.PairModel;

import java.util.Date;
import java.util.List;

public class JournalModel
{
   private LessonModel lesson;
   private List<PairModel> pairs;
   private List<StudentModel> students;
   private List<Date> dates;
   private List<PointModel> journalCell;

   public JournalModel()
   {
   }

   public LessonModel getLesson() {
      return lesson;
   }
   public void setLesson(LessonModel lesson) {
      this.lesson = lesson;
   }

   public List<StudentModel> getStudents() {
      return students;
   }
   public void setStudents(List<StudentModel> students) {
      this.students = students;
   }

   public List<Date> getDates() {
      return dates;
   }
   public void setDates(List<Date> dates) {
      this.dates = dates;
   }

   public List<PointModel> getJournalCell() {
      return journalCell;
   }
   public void setJournalCell(List<PointModel> journalCell) {
      this.journalCell = journalCell;
   }

   public List<PairModel> getPairs() {
      return pairs;
   }
   public void setPairs(List<PairModel> pairs) {
      this.pairs = pairs;
   }

}
