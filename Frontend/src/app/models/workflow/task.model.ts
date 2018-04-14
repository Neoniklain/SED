import {User} from "../account/user.model";

export class TaskDescription {
  public id: number;
  public name: string;
  public creator: User;
  public subTasks: Task[];
  public users: User[];
  public description: string;
  public globalStatus: string;
  constructor() {
    this.name = "";
    this.creator = new User();
    this.users = [];
    this.globalStatus = "";
    this.description = "";
    this.subTasks = [];
  }
}

export class Task {
    public id: number;
    public executor: User;
    public status: string;
    public response: string;
    constructor() {
        this.executor = new User();
        this.status = "";
        this.response = "";
    }
}
