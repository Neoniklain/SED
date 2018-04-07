import {Role} from "./role.model";

export class User {
    id: number;
    username: string;
    userFIO: string;
    email: string;
    roles: Array<Role>;

    constructor() {
        this.id = 0;
        this.username = "";
        this.userFIO = "";
        this.email = "";
        this.roles = new Array<Role>();
    }
}

export class LogInUser {
    username: string;
    password: string;
    email: string;
}

export class UserCreate {
    username: string;
    password: string;
    userFIO: string;
    email: string;
    roles: Array<Role>;

    constructor() {
        this.username = "";
        this.email = "";
        this.userFIO = "";
        this.password = "";
        this.roles = new Array<Role>();
    }
}
