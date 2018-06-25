import {Room} from "./room.model";
import {Lesson} from "./lesson";
import {PairType} from "./pairType";

export class Pair {
    public id: number;
    public pairNumber: number;
    public dayofweek: String;
    public weektype: String;
    public room: Room;
    public pairType: PairType;
    public lesson: Lesson;

    constructor() {
        this.id = 0;
        this.pairNumber = 0;
        this.weektype = "";
        this.dayofweek = "";
        this.lesson = new Lesson();
        this.pairType = new PairType();
        this.room = new Room();
    }
}
