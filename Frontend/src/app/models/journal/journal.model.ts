import {Student} from "../student";

export class Journal {
   public students: Array<Student>;
   public dates: number[];
   public journalCell: Array<JournalCell>;
   constructor() {
      this.students = new Array();
      this.dates = [];
      this.journalCell = new Array();
   }
}

export class JournalCell {
   public student: Student;
   public date: number;
   public mark: number;
}

