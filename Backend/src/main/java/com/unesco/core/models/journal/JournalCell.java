package com.unesco.core.models.journal;

import com.unesco.core.models.StudentModel;

public class JournalCell {
   private StudentModel student;
   private String date;
   int mark;

   public StudentModel getStudent() {
      return student;
   }
   public void setStudent(StudentModel student) {
      this.student = student;
   }

   public String getDate() {
      return date;
   }
   public void setDate(String date) {
      this.date = date;
   }

   public int getMark() {
      return mark;
   }
   public void setMark(int mark) {
      this.mark = mark;
   }

   JournalCell(StudentModel _student, String _date, int _mark) {
      student = _student;
      date = _date;
      mark = _mark;
   }

   JournalCell(StudentModel _student, String _date) {
      student = _student;
      date = _date;
      mark = 0;
   }
}
