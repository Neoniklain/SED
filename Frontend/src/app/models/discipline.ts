
import {FieldOfKnowledge} from "./fieldOfKnowledge";

export class Discipline {
    id: number;
    /** Поле название */
    name: string;
    /** Поле раздел знаний */
    fieldOfKnowledge: FieldOfKnowledge;

    constructor() {
        this.id = 0;
        this.name = "";
        this.fieldOfKnowledge = new FieldOfKnowledge();
    }
}