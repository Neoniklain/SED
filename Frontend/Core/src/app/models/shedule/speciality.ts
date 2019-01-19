import {Department} from "./department";

export class Speciality {
    id: number;
    name: string;
    department: Department;

    constructor() {
        this.id = 0;
        this.name = '';
        this.department = new Department;
    }
}