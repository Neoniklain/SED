import {Role} from "./role.model";

export class User {
  username: string;
  userFIO: string;
  email: string;
  roles: Array<Role>;
}

export class LogInUser {
  username: string;
  password: string;
  email: string;
}

