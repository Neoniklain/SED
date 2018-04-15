import {Group} from "../group";
import {Discipline} from "../discipline";
import {Room} from "../room.model";
import {Professor} from "../professor";

export class Pair {
    public id: number;
    public pairNumber: number;
    public dayofweek: String;
    public weektype: String;
    public discipline: Discipline;
    public room: Room;
    public professor: Professor;
    public group: Group;

    constructor() {
        this.id = 0;
        this.pairNumber = 0;
        this.weektype = "";
        this.dayofweek = "";
        this.professor = new Professor();
        this.room = new Room();
        this.discipline = new Discipline();
        this.group = new Group();
    }
}
