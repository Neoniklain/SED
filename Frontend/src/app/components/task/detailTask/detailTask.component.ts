import {Component, EventEmitter, Input, Output} from "@angular/core";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {NotificationService} from "../../../services/notification.service";
import {AuthenticationService} from "../../../services/authService";
import {FileService} from "../../../services/file.service";
import {TaskDescription} from "../../../models/task/task.model";

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
        this.CheckICreate();
    }

    public CheckICreate() {
    }

    public isNeedAnswer() {
    }

    public Close() {
        this.localTD = new TaskDescription();
        this.onClose.emit();
    }

    public GetTypeName(): string {
        return "→→ Тип задачи ←←";
    }
}
