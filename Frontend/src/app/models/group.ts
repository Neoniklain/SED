import {Department} from "./department";

export class Group {
    public id: number;
    public name: string;
    public department: Department;

    constructor() {
        this.id = 0;
        this.name = "";
        this.department = new Department();
    }
}
