import {User} from "../account/user.model";
import {FileDescription} from "../file/file.model";

export class TaskDescription {
  public id: number;
  public name: string;
  public creator: User;
  public taskUsers: TaskUser[];
  public users: User[];
  public description: string;
  public status: number;
  public statusName: string;
  public files: FileDescription[];
  constructor() {
    this.name = "";
    this.creator = new User();
    this.users = [];
    this.status = 0;
    this.statusName = "";
    this.description = "";
    this.taskUsers = [];
    this.files = [];
  }
}

export class TaskUser {
    public id: number;
    public executor: User;
    public statusName: string;
    public status: number;
    public response: string;
    public files: FileDescription[];
    constructor() {
        this.status = 0;
        this.response = "";
        this.files = [];
    }
}

export enum TaskStatusType{
    Processed = 0,
    Denied = 1,
    Completed = 2,
    Checked = 3,
    Viewed = 4,
    SentToRevision = 5,
    SentToReview = 6
}

/*export class TaskStatusList {
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
}*/