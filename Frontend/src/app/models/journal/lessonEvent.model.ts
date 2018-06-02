import {Lesson} from "../shedule/lesson";
import {PointType} from "./journal.model";

export class LessonEvent {

    public id: number;
    public date: Date;
    public type: PointType;
    public lesson: Lesson;
    public comment: string;

    constructor() {
        this.id = 0;
        this.type = new PointType();
        this.lesson = new Lesson();
        this.comment = "";
    }
}