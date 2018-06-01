import {User, UserCreate} from "./user.model";
import {Group} from "../shedule/group";

export class Student extends User {
    public group: Group;

     constructor() {
        super();
        this.group = new Group();
    }
}
