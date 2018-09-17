import {Component, EventEmitter, Input, Output, ViewChild} from "@angular/core";
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
import {WorkTaskComponent} from "../workTask/workTask.component";
import {UserSearchComponent} from "../../shared/userSearch/userSearch";

@Component({
    selector: 'new-task-desc',
    templateUrl: './newTaskDesc.component.html'
})

export class NewTaskDescComponent {
    @Input()
    isModal: boolean = false;

    @Output() onCreateNew: EventEmitter<any> = new EventEmitter();
    @Output() onSizeChange: EventEmitter<any> = new EventEmitter();
    @Output() onClose: EventEmitter<any> = new EventEmitter();

    @ViewChild(UserSearchComponent)
    userSearchComponent: UserSearchComponent;

    public localTD: TaskDescription;
    public title: string = "";
    public listOfTypes: any[];
    // ↓ Это нужно для работы enum во вью.
    public TaskStatusType = TaskStatusType;
    public selectedType: any;
    public show: boolean = false;
    public showSelectUserForm: boolean = true;

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        this.localTD = new TaskDescription();
        this.title = "Создать задачу";
        this.listOfTypes = [];
        this.listOfTypes.push({name: "Без уведомления", value: TaskType.Info.valueOf()});
        this.listOfTypes.push({name: "С уведомлением", value: TaskType.Notice.valueOf()});
        this.listOfTypes.push({name: "С ответом", value: TaskType.Answer.valueOf()});
        this.selectedType = this.listOfTypes[0];
    }

    public showDialog(td?: TaskDescription) {
        this.localTD = new TaskDescription();
        if (td) {
            this.localTD = td;
            this.title = "Редактирование задачи";
        }
        else {
            this.title = "Создание задачи";
        }
        this.show = true;
    }

    public closeDialog() {
        this.show = false;
        this.localTD = new TaskDescription();
        this.onClose.emit();
    }

    public CreateTask() {
        this.localTD.type = this.selectedType.value;
        this.taskService.Create(this.localTD).subscribe((res) => {
                this.show = false;
                this.notificationService.FromStatus(res);
                this.onCreateNew.emit(res.data);
                this.onClose.emit();
            },
            (error: any) => {
                console.error("Ошибка", error);
            });
    }

    public ChangeStatusTU(item: TaskUser, status: number) {
        item.status = status;
        this.taskService.ChangeStatusTaskUser(item.id, status)
            .subscribe((res) => {
                this.notificationService.FromStatus(res);
                item.statusName = TaskStatusType[status];
            }, (error: any) => {
                console.error("Ошибка", error);
            });
    }

    public setUsers(users: User[]) {
        this.showSelectUserForm = false;
        this.localTD.taskUsers = [];
        for (let user of users) {
            let tempTU = new TaskUser();
            tempTU.dateRequired = this.localTD.dateRequired;
            tempTU.executor = user;
            this.localTD.taskUsers.push(tempTU);

        }
    }

    public showUserSearchForm() {
        let users: User[] = [];
        for (let item of this.localTD.taskUsers) {
            users.push(item.executor);
        }
        this.showSelectUserForm = true;
        this.userSearchComponent.Edit(users);
    }

    public setFull() {
        this.isModal = false;
        this.onSizeChange.emit(false);
    }

    public setDialog() {
        this.isModal = true;
        this.onSizeChange.emit(true);
    }
}
