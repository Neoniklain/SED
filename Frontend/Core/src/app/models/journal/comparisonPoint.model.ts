import {PointType} from "./journal.model";
import {Pair} from "../shedule/pair";

export class ComparisonPoint {
    type: PointType;
    pair: Pair;

    constructor() {
        this.type = new PointType();
        this.pair = new Pair();
    }
}