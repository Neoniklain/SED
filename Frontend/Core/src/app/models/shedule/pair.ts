import {Room} from "./room.model";
import {Lesson, ScheduleShowedLesson} from "./lesson";
import {PairType} from "./pairType";
import {WeekType} from "./weekType.enum";

export class Pair {
    public id: number;
    public pairNumber: number;
    public dayofweek: String;
    public weektype: String;
    public room: Room;
    public pairType: PairType;
    public lesson: Lesson;
    public optionally: boolean;
    public subgroup: number;
    public flow: boolean;

    constructor() {
        this.id = 0;
        this.pairNumber = 0;
        this.weektype = WeekType.Все.toString();
        this.dayofweek = "";
        this.lesson = new Lesson();
        this.pairType = new PairType();
        this.room = new Room();
        this.optionally = false;
        this.flow = false;
        this.subgroup = 0;
    }
}

export class ScheduleShowedPairs {
    public id: number;
    public pairNumber: number;
    public dayofweek: String;
    public weektype: String;
    public room: Room;
    public pairType: PairType;
    public lesson: ScheduleShowedLesson;
    public optionally: boolean;
    public subgroup: number;
    public flow: boolean;

    constructor(p: Pair) {
        this.id = p.id;
        this.pairNumber = p.pairNumber;
        this.weektype = p.weektype;
        this.dayofweek = p.dayofweek;
        this.pairType = p.pairType;
        this.room = p.room;
        this.optionally = p.optionally;
        this.flow = p.flow;
        this.subgroup = p.subgroup;
        let newShowedLesson = new ScheduleShowedLesson(p.lesson);
        this.lesson = newShowedLesson;
    }
}