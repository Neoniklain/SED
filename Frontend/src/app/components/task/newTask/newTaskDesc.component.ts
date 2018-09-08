import {Component, EventEmitter, Output} from '@angular/core';
import {TaskDescription, TaskStatusType, TaskType, TaskUser} from "../../../models/task/task.model";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {User} from "../../../models/account/user.model";
import {NotificationService} from "../../../services/notification.service";
import {FileUploader, FileUploaderOptions} from "ng2-file-upload";
import {AuthenticationService} from "../../../services/authService";
import {ApiRouteConstants, BaseApiUrl} from "../../../bootstrap/app.route.constants";
import {FileDescription} from "../../../models/file/file.model";
import {FileService} from "../../../services/file.service";

@Component({
    selector: 'new-task-desc',
    templateUrl: './newTaskDesc.component.html'
})

export class NewTaskDescComponent {
    public _uploader: FileUploader;
    public _fileOptions: FileUploaderOptions;
    public myFiles: File[];
    public maxFileSize: number = 1000 * 1000 * 10;
    public localTD: TaskDescription;
    public _show: boolean = false;
    public _isCreate: boolean = true;
    public _title: string = '';
    public listOfTypes: any[];
    public taskType: any;
    // ↓ Это нужно для работы enum во вью.
    public TaskStatusType = TaskStatusType;

    // возвращаем результат
    @Output() onCreateNew: EventEmitter<any> = new EventEmitter();

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        this.localTD = new TaskDescription();
        this.myFiles = [];
        this._fileOptions = {
            url: "",
            maxFileSize: 50 * 1000 * 1000,
            headers: [
                { name: 'Authorization', value: this.authService.getToken() }
            ]
        };
        this._uploader = new FileUploader(this._fileOptions);

        this.listOfTypes = [];
        this.listOfTypes.push({name: "Без уведомления", value: TaskType.Info.valueOf()});
        this.listOfTypes.push({name: "С уведомлением", value: TaskType.Notice.valueOf()});
        this.listOfTypes.push({name: "С ответом", value: TaskType.Answer.valueOf()});
        this.taskType = this.listOfTypes[0];
    }

    public setAttributeshowDialog(td?: TaskDescription) {
        this.localTD = new TaskDescription();
        this._isCreate = true;
        this._uploader = new FileUploader(this._fileOptions);
        if (td) {
            this.localTD = td;
            this._title = "Редактирование задачи";
            this._isCreate = false;
        }
        else {
            this._title = "Создание задачи";
        }
        this._show = true;
    }

    public CreateTask() {
        if (this._isCreate) {
            if (this.localTD.users.length > 0) {
                this.localTD.type = this.taskType.value;
                this.taskService.Create(this.localTD).subscribe((res) => {
                        this._show = false;
                        if (this._uploader.queue.length > 0) {
                            let url = BaseApiUrl + ApiRouteConstants.File.AddFileForTD + res.data.id;
                            this._uploader.options.url = url;
                            for (let i = 0; i < this._uploader.queue.length; i++) {
                                this._uploader.queue[i].url = url;
                                this._uploader.queue[i].upload();
                            }
                        }
                        this.notificationService.FromStatus(res);
                        this.onCreateNew.emit(res.data);
                    },
                    (error: any) => {
                        console.error("Ошибка" + error);
                    });
            }
        }
        else {
            this.taskService.Update(this.localTD).subscribe((res) => {
                    this._show = false;
                },
                (error: any) => {
                    console.error("Ошибка" + error);
                });
        }
    }

    public ChangeStatus(item: TaskUser, status: number) {
        item.status = status;
        this.taskService.AnswerTask(item)
            .subscribe((res) => {
                this.notificationService.FromStatus(res);
                item.statusName = TaskStatusType[status];
            }, (error: any) => {
                console.error(error);
            });
    }

    public setUsers(users: User[]) {
        this.localTD.users = users;
    }

    public downloadFile(item: FileDescription) {
        this.fileService.downloadFile(item.id);
    }
}
