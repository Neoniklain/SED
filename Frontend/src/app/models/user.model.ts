import {Role} from "./role.model";

export class User {
  username: string;
  email: string;
  roles: Array<Role>;
}

export class LogInUser {
  username: string;
  password: string;
  email: string;
}

