package com.unesco.core.models.journal;

public class JournalCell {
   private Student student;
   private int date;
   int mark;

   public Student getStudent() {
      return student;
   }
   public void setStudent(Student student) {
      this.student = student;
   }

   public int getDate() {
      return date;
   }
   public void setDate(int date) {
      this.date = date;
   }

   public int getMark() {
      return mark;
   }
   public void setMark(int mark) {
      this.mark = mark;
   }

   JournalCell(Student _student, int _date, int _mark) {
      student = _student;
      date = _date;
      mark = _mark;
   }

   JournalCell(Student _student, int _date) {
      student = _student;
      date = _date;
      mark = 0;
   }
}
