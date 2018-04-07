
import {FieldOfKnowledge} from "./fieldOfKnowledge";

export class Discipline {
    private id: number;
    /** Поле название */
    private name: string;
    /** Поле раздел знаний */
    private fieldOfKnowledge: FieldOfKnowledge;

    constructor() {
        this.id = 0;
        this.name = "";
        this.fieldOfKnowledge = new FieldOfKnowledge();
    }
}