import {Role} from "./role.model";
import {Issue} from "./issue.model";

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

export class TestUser {
    User: User;
    Issues: Issue[];
}

