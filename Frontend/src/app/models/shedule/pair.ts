import {Room} from "./room.model";
import {Lesson} from "./lesson";

export class Pair {
    public id: number;
    public pairNumber: number;
    public dayofweek: String;
    public weektype: String;
    public room: Room;
    public lesson: Lesson;

    constructor() {
        this.id = 0;
        this.pairNumber = 0;
        this.weektype = "";
        this.dayofweek = "";
        this.lesson = new Lesson();
        this.room = new Room();
    }
}
