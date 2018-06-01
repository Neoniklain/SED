import {Student} from "../account/student";
import {Lesson} from "../shedule/lesson";
import {Pair} from "../shedule/pair";

export class Journal {
   public students: Array<Student>;
   public dates: Date[];
   public journalCell: Array<JournalCell>;
   public lesson: Lesson;
   public pairs: Array<Pair>;
   constructor() {
      this.students = new Array();
      this.dates = [];
      this.journalCell = new Array();
      this.pairs = new Array();
   }
}

export class JournalCell {

   public id: number;
   public value: number;
   public student: Student;
    public date: Date;
    public type: PointType;
    public pair: Pair;

    constructor() {
        this.id = 0;
        this.value = 0;
        this.student = new Student();
        this.type = new PointType();
        this.pair = new Pair();
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

