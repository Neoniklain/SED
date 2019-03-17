import {User} from "../account/user.model";
import {FileDescription} from "../file/file.model";

export class TaskDescription {
  public id: number;
  public name: string;
  public creator: User;
  public taskUsers: TaskUser[];
  public description: string;
  public type: number;
  public status: number;
  public files: FileDescription[];
  public dateCreate: Date;
  public dateRequired: Date;
  constructor() {
    this.name = "";
    this.creator = new User();
    this.type = -1;
    this.status = 0;
    this.description = "";
    this.taskUsers = [];
    this.files = [];
    this.dateRequired = null;
  }
}

export class TaskUser {
    public id: number;
    public executor: User;
    public status: number;
    public response: string;
    public files: FileDescription[];
    public dateCreate: Date;
    public dateRequired: Date;
    constructor() {
        this.status = 0;
        this.response = "";
        this.files = [];
    }
}

/**
 * Processed В процессе
 * Denied Отклонено
 * Completed Завершено
 * Checked Проверено (вероятно то же самое, что и Просмотрено, только для автора, но это не точно)
 * Viewed Просмотрено
 * SentToRevision Отправлено на доработку
 * SentToReview Отправлено на проверку
 */
export enum TaskStatusType {
    // В процессе
    Processed = 0,
    // Отклонено
    Denied = 1,
    // Завершено
    Completed = 2,
    // Проверено (вероятно то же самое, что и Просмотрено, только для автора, но это не точно)
    Checked = 3,
    // Просмотрено
    Viewed = 4,
    // Отправлено на доработку
    SentToRevision = 5,
    // Отправлено на проверку
    SentToReview = 6
}
/**
 * Info Без уведомления
 * Notice С уведомлением
 * Answer С ответом
 */
export enum TaskType {
    // Без уведомления
    Info = 0,
    // С уведомлением о прочтении
    Notice = 1,
    // С ответом
    Answer = 2
}