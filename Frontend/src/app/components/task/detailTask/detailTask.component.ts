import {Component, Input} from "@angular/core";
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

    public show: boolean = false;
    public localTD: TaskDescription;
    public ICreate: boolean = false;

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        this.localTD = new TaskDescription();
        this.CheckICreate();
    }

    public CheckICreate() {
    }

    public isNeedAnswer() {
    }

    public Show(td: TaskDescription) {
        if (td != null) {
            this.show = true;
            this.localTD = td;
        }
    }

    public Close() {
        this.show = false;
        this.localTD = new TaskDescription();
    }

    public GetTypeName(): string {
        return "→→ Тип задачи ←←";
    }
}
