import {Institute} from "./institute";

export class Department {
    public id: number;
    public name: String;
    public institute: Institute;

    constructor() {
        this.id = 0;
        this.name = "";
        this.institute = new Institute();
    }
}