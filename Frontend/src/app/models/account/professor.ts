import {Department} from "../shedule/department";
import {User, UserCreate} from "./user.model";

export class Professor {
    public id: number;
    public department: Department;
    public user: User;


     constructor() {
        this.id = 0;
        this.user = new User();
        this.department = new Department();
    }
}
