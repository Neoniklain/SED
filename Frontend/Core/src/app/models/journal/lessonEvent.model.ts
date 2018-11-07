import {Lesson} from "../shedule/lesson";
import {PointType} from "./journal.model";
import {Pair} from "../shedule/pair";

export class LessonEvent {

    public id: number;
    public date: Date;
    public type: PointType;
    public pairs: Array<Pair>;
    public maxValue: number;
    public lesson: Lesson;
    public comment: string;

    constructor() {
        this.id = 0;
        this.type = new PointType();
        this.lesson = new Lesson();
        this.comment = "";
        this.pairs = [];
    }
}