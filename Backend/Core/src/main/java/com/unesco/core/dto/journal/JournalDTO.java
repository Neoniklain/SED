package com.unesco.core.dto.journal;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.shedule.LessonDTO;
import com.unesco.core.dto.shedule.PairDTO;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class JournalDTO
{
   private LessonDTO lesson;
   private List<StudentDTO> students;
   private List<ComparisonDTO> comparison;
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

   public List<ComparisonDTO> getComparison() {
      return comparison;
   }
   public void setComparison(List<ComparisonDTO> comparison) {
      this.comparison = comparison;
   }

   public List<PointDTO> getJournalCell() {
      return journalCell;
   }
   public void setJournalCell(List<PointDTO> journalCell) {
      this.journalCell = journalCell;
   }

}
