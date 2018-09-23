import {Discipline} from "./discipline";
import {Professor} from "../account/professor";
import {Group} from "./group";

export class Lesson {

    public id: number;
    public discipline: Discipline;
    public professor: Professor;
    public group: Group;

    constructor() {
        this.id = 0;
        this.professor = new Professor();
        this.discipline = new Discipline();
        this.group = new Group();
    }

}