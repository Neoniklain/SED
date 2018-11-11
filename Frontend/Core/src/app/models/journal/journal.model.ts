import {Student} from "../account/student";
import {Lesson} from "../shedule/lesson";
import {ComparisonPoint} from "./comparisonPoint.model";

export class Journal {
   public students: Array<StudentJournal>;
   public comparison: Array<Comparison>;
   public journalCell: Array<JournalCell>;
   public lesson: Lesson;
   constructor() {
      this.students = [];
      this.journalCell = [];
      this.comparison = [];
   }
}

export class Comparison {
    public date: Date;
    public points: Array<ComparisonPoint>;

    constructor() {
        this.date = new Date();
        this.points = [];
    }
}

export class JournalCell {

   public id: number;
   public value: number;
   public studentId: number;
    public date: Date;
    public type: PointType;
    public pairId: number;
    public wasChange: boolean;

    constructor() {
        this.id = 0;
        this.value = 0;
        this.studentId = 0;
        this.type = new PointType();
        this.pairId = 0;
        this.wasChange = false;
    }
}

export class PointType {

    public id: number;
    public name: String;

    constructor() {
        this.id = 0;
        this.name = "";
    }

}

export class StudentJournal {
    public student: Student;
    public subgroup: number;

    constructor() {
        this.student = new Student();
        this.subgroup = 0;
    }

}

export class StudentJournalList {
    public studentJournal: Array<StudentJournal>;
    public lesson: Lesson;

    constructor() {
        this.studentJournal = [];
        this.lesson = new Lesson();
    }

}

