import {Department} from "../shedule/department";
import {User, UserCreate} from "./user.model";

export class Professor extends User {
    public department: Department;

     constructor() {
        super();
        this.department = new Department();
    }
}
