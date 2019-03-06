import {Discipline} from "./discipline";
import {Professor} from "../account/professor";
import {Group} from "./group";
import {SemesterNumberYear} from "../semesterNumberYear.model";

export class Lesson {

    public id: number;
    public discipline: Discipline;
    public professor: Professor;
    public group: Group;
    public semesterNumberYear: SemesterNumberYear;

    constructor() {
        this.id = 0;
        this.professor = new Professor();
        this.discipline = new Discipline();
        this.group = new Group();
        this.semesterNumberYear = new SemesterNumberYear();
    }
}

export class ScheduleShowedLesson {
    public id: number;
    public discipline: Discipline;
    public professor: Professor;
    public groups: Array<Group>;
    public semesterNumberYear: SemesterNumberYear;

    constructor(lesson: Lesson) {
        this.groups = [];
        this.id = lesson.id;
        this.discipline = lesson.discipline;
        this.groups.push(lesson.group);
        this.professor = lesson.professor;
        this.semesterNumberYear = new SemesterNumberYear();
    }

}
