import {Institute} from "./institute";

export class Department {
    private id: number;
    private name: String;
    private institute: Institute;

    constructor() {
        this.id = 0;
        this.name = "";
        this.institute = new Institute();
    }
}