import {Department} from "./department";

export class Group {
    private id: number;
    private name: string;
    private department: Department;

    constructor() {
        this.id = 0;
        this.name = "";
        this.department = new Department();
    }
}
