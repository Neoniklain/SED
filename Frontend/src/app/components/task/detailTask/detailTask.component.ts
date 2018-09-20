import {Component, EventEmitter, Input, Output} from "@angular/core";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {NotificationService} from "../../../services/notification.service";
import {AuthenticationService} from "../../../services/authService";
import {FileService} from "../../../services/file.service";
import {TaskDescription} from "../../../models/task/task.model";
import {FileDescription, ObjectType} from "../../../models/file/file.model";
import {StatusType} from "../../../models/statusType.model";

@Component({
    selector: 'detail-task',
    templateUrl: './detailTask.component.html'
})

export class DetailTaskComponent {
    @Input()
    isModal: boolean = false;
    @Input()
    localTD: TaskDescription;
    @Output()
    onClose: EventEmitter<any> = new EventEmitter();
    public ICreate: boolean = false;
    public isShowFiles: boolean = false;

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        if (this.localTD == null) {
            this.localTD = new TaskDescription();
            this.localTD.name = "Задача не существует";
        }
        else {
            this.checkFilesTD(this.localTD);
        }
        this.CheckICreate();
    }

    public CheckICreate() {
    }

    public isNeedAnswer() {
    }

    checkFilesTD(item: TaskDescription) {
        if (item.files != null) {
            if (item.files.length > 0) {
                this.isShowFiles = true;
            }
        }
        this.fileService.getFilesForObject(ObjectType.TaskDescription, item.id)
            .subscribe(
                res => {
                    if (res.status == StatusType[StatusType.OK]) {
                        item.files = res.data;
                        if (item.files != null) {
                            if (item.files.length > 0) {
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
        return "→→ Тип задачи ←←";
    }

    public downloadFile(file: FileDescription) {
        this.fileService.downloadFile(file.id);
    }
}
