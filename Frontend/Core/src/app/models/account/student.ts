import {User, UserCreate} from "./user.model";
import {Group} from "../shedule/group";

export class Student {
    public id: number;
    public group: Group;
    public user: User;

     constructor() {
         this.id = 0;
        this.user = new User();
        this.group = new Group();
    }
}
