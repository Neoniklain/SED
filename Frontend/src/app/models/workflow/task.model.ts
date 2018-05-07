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
        this.status = "";
        this.response = "";
    }
}

export class TaskStatusList {
    public Processed: string = "Processed";
    public Denied: string = "Denied";
    public Completed: string = "Completed";
    public Checked: string = "Checked";
    public Viewed: string = "Viewed";
    public SentToRevision: string = "SentToRevision"
    public SentToReview: string = "SentToReview";
    constructor() {
        this.Processed = "Processed";
        this.Denied = "Denied";
        this.Completed = "Completed";
        this.Checked = "Checked";
        this.Viewed = "Viewed";
        this.SentToRevision = "SentToRevision";
        this.SentToReview = "SentToReview";
    }
}