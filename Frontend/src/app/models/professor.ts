import {Department} from "./department";
import {User, UserCreate} from "./account/user.model";

export class Professor extends User {
    public department: Department;

     constructor() {
        super();
        this.department = new Department();
    }
}
