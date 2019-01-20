import {Department} from "./department";
import {Institute} from "./institute";

export class Speciality {
    id: number;
    name: string;
    code: string;
    department: Department;
    institute: Institute;

    constructor() {
        this.id = 0;
        this.name = '';
        this.code = '';
        this.department = new Department;
        this.institute = new Institute;
    }
}