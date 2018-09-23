import {Component, EventEmitter, Input, Output} from "@angular/core";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {NotificationService} from "../../../services/notification.service";
import {AuthenticationService} from "../../../services/authService";
import {FileService} from "../../../services/file.service";
import {TaskDescription, TaskStatusType, TaskType, TaskUser} from "../../../models/task/task.model";
import {FileDescription, ObjectType} from "../../../models/file/file.model";
import {StatusType} from "../../../models/statusType.model";
import {User} from "../../../models/account/user.model";
import {Globals} from "../../../globals";
import {viewAttached} from "@angular/core/src/render3/instructions";

@Component({
    selector: 'detail-task',
    templateUrl: './detailTask.component.html'
})

export class DetailTaskComponent {
    @Input()
    localTD: TaskDescription;
    @Output()
    onClose: EventEmitter<any> = new EventEmitter();
    @Output()
    onReturn: EventEmitter<any> = new EventEmitter();
    public ICreate: boolean = false;
    public isShowFiles: boolean = false;
    public isNeedAnswer: boolean = false;
    public myTaskUser: TaskUser;
    public user: User;
    public viewName: string = 'detail';
    // ↓ Нужно для работы enum на View
    public TaskStatusType = TaskStatusType;

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private globals: Globals,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        if (this.localTD == null) {
            this.localTD = new TaskDescription();
            this.localTD.name = "Задача не существует";
        }
        else {
            this.CheckICreate();
            this.checkFilesTD();
            this.checkTaskUser();
        }
    }

    public setComp(name: string) {
        this.viewName = name;
    }

    public letShow(name: string): boolean {
        if (name == this.viewName) {
            return true;
        }
        return false;
    }

    public CheckICreate() {
        this.globals.getUser.subscribe(
            result => {
                this.user = result;
                if (this.localTD.creator.id == this.user.id) {
                    this.ICreate = true;
                }
                else {
                    this.ICreate = false;
                }
            }
        );
    }

    public CheckIsNeedAnswer() {
        this.isNeedAnswer = false;
        if (this.ICreate) {
            this.isNeedAnswer = false;
        }
        else {
            if (this.localTD.status != TaskStatusType.Completed) {
                if (this.myTaskUser != null) {
                    if (this.myTaskUser.status == TaskStatusType.Processed) {
                        this.isNeedAnswer = true;
                    }
                    if (this.myTaskUser.status == TaskStatusType.SentToRevision) {
                        this.isNeedAnswer = true;
                    }
                }
            }
        }
    }

    private checkTaskUser() {
        this.taskService.GetTaskUserByTD(this.localTD.id, !this.ICreate)
            .subscribe(
                res => {
                    if (res.status == StatusType[StatusType.OK]) {
                        this.localTD.taskUsers = [];
                        this.localTD.taskUsers = res.data;
                        if (this.ICreate) {
                            // Здесь возможно будет изменение статуса о прочтении
                            // Но пока что решил без этого
                        }
                        else {
                            this.myTaskUser = this.localTD.taskUsers[0];
                            if (this.localTD.type == TaskType.Notice) {
                                if (this.myTaskUser.status != TaskStatusType.Completed) {
                                    this.ChangeStatusTU(this.myTaskUser, TaskStatusType.Completed);
                                }
                            }
                            this.CheckIsNeedAnswer();
                            // Здесь ТОЖЕ возможно нужен статус о прочтении, но другой
                            // Ну переменную по другому назвать
                            // Типа чтобы автор видел, что тот, кому назначена задача
                            // Увидел, что ему она поставлена
                        }
                    }
                    else {
                        this.localTD.taskUsers = [];
                        this.notificationService.FromStatus(res);
                    }
                },
                error => {
                    this.localTD.taskUsers = [];
                    console.error(error);
                }
            );
    }

    private checkFilesTD() {
        if (this.localTD.files != null) {
            if (this.localTD.files.length > 0) {
                this.isShowFiles = true;
            }
        }
        this.fileService.getFilesForObject(ObjectType.TaskDescription, this.localTD.id)
            .subscribe(
                res => {
                    if (res.status == StatusType[StatusType.OK]) {
                        this.localTD.files = res.data;
                        if (this.localTD.files != null) {
                            if (this.localTD.files.length > 0) {
                                this.isShowFiles = true;
                            }
                        }
                        else {
                            this.isShowFiles = false;
                        }
                    }
                    else {
                        this.isShowFiles = false;
                    }
                },
                error => {
                    console.error(error);
                    this.isShowFiles = false;
                }
            );
    }

    public Close() {
        this.localTD = new TaskDescription();
        this.onClose.emit();
    }

    public GetTypeName(): string {
        return TaskType[this.localTD.type];
    }

    public getStatusName(statusId: number) {
        return TaskStatusType[statusId];
    }

    public downloadFile(file: FileDescription) {
        this.fileService.downloadFile(file.id);
    }

    public ChangeStatusTU(item: TaskUser, status: number) {
        item.status = status;
        this.taskService.ChangeStatusTaskUser(item.id, status)
            .subscribe((res) => {
                this.notificationService.FromStatus(res);
            }, (error: any) => {
                console.error("Ошибка", error);
            });
    }

    public onAnswer(TU: TaskUser) {
        this.myTaskUser = TU;
        this.CheckIsNeedAnswer();
        this.setComp('detail');
        this.onReturn.emit(this.myTaskUser);
    }

    public DeleteTaskUser(tu_id: number) {
        this.taskService.DeleteTaskUser(tu_id)
            .subscribe(
                res => {
                    this.notificationService.FromStatus(res);
                },
                error => {
                    console.error(error);
                }
            );
    }
}
