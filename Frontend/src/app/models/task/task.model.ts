import {User} from "../account/user.model";
import {FileDescription} from "../file/file.model";

export class TaskDescription {
  public id: number;
  public name: string;
  public creator: User;
  public taskUsers: TaskUser[];
  public users: User[];
  public description: string;
  public type: number;
  public status: number;
  public statusName: string;
  public files: FileDescription[];
  constructor() {
    this.name = "";
    this.creator = new User();
    this.users = [];
    this.type = -1;
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

export enum TaskStatusType {
    // В процессе
    Processed = 0,
    // Отклонено
    Denied = 1,
    // Завершено
    Completed = 2,
    // Проверено
    Checked = 3,
    // Просмотрено (вероятно то же самое, что и Проверено)
    Viewed = 4,
    // Отправить на доработку
    SentToRevision = 5,
    // Отправить на проверку
    SentToReview = 6
}

export enum TaskType {
    // Без уведомления
    Info = 0,
    // С уведомлением о прочтении
    Notice = 1,
    // С ответом
    Answer = 2
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