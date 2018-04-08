import {User, UserCreate} from "./account/user.model";
import {Group} from "./group";

export class Student extends User {
    public group: Group;

     constructor() {
        super();
        this.group = new Group();
    }
}
