import {Component, EventEmitter, Input, Output, ViewChild} from "@angular/core";
import {TaskDescription, TaskStatusType, TaskType, TaskUser} from "../../../models/task/task.model";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/account.service";
import {User} from "../../../models/account/user.model";
import {NotificationService} from "../../../services/notification.service";
import {FileUploader, FileUploaderOptions} from "ng2-file-upload";
import {AuthenticationService} from "../../../services/auth.service";
import {ApiRouteConstants, BaseApiUrl} from "../../../bootstrap/app.route.constants";
import {FileDescription, ObjectType} from "../../../models/file/file.model";
import {FileService} from "../../../services/file.service";
import {WorkTaskComponent} from "../workTask/workTask.component";
import {UserSearchComponent} from "../../shared/userSearch/userSearch";
import {ResponseType} from "@angular/http";
import {ResponseStatus} from "../../../models/additional/responseStatus";
import {StatusType} from "../../../models/statusType.model";
import {FileUpload} from "primeng/primeng";

@Component({
    selector: 'new-task-desc',
    templateUrl: './newTaskDesc.component.html'
})

export class NewTaskDescComponent {
    @Input()
    isModal: boolean = false;
    @Input()
    localTD: TaskDescription;
    @Output()
    onCreateNew: EventEmitter<any> = new EventEmitter();
    @Output()
    onClose: EventEmitter<any> = new EventEmitter();

    @ViewChild(UserSearchComponent)
    userSearchComponent: UserSearchComponent;
    @ViewChild(FileUpload)
    primeFileUploader: FileUpload;

    public title: string = "";
    public listOfTypes: any[];
    // ↓ Это нужно для работы enum во вью.
    public TaskStatusType = TaskStatusType;
    public selectedType: any;
    public showSelectUserForm: boolean = false;
    public ru: any;

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        this.ru = {
            firstDayOfWeek: 1,
            dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
            dayNamesShort: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб"],
            dayNamesMin: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб"],
            monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
            monthNamesShort: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
            today: 'Сегодня',
            clear: 'Очистить'
        };
        if (this.localTD == null) {
            this.localTD = new TaskDescription();
            this.title = "Создание задачи";
        }
        else {
            this.title = "Редактирование задачи";
        }
        this.listOfTypes = [];
        this.listOfTypes.push({name: "Без уведомления", value: TaskType.Info.valueOf()});
        this.listOfTypes.push({name: "С уведомлением", value: TaskType.Notice.valueOf()});
        this.listOfTypes.push({name: "С ответом", value: TaskType.Answer.valueOf()});
        this.selectedType = this.listOfTypes[0];
    }

    public CreateTask() {
        this.localTD.type = this.selectedType.value;
        this.taskService.Create(this.localTD).subscribe((res) => {
                this.notificationService.FromStatus(res);
                if (res.status == StatusType[StatusType.OK]) {
                    let url = BaseApiUrl + ApiRouteConstants.File.AddFileForObject
                        .replace(":objectTypeId", ObjectType.TaskDescription.toString())
                        .replace(":objectId", res.data.id.toString());
                    this.uploadFiles(url);
                    this.onCreateNew.emit(res.data);
                    this.onClose.emit();
                }
            },
            (error: any) => {
                console.error("Ошибка", error);
            });
    }

    public CloseDialog() {
        this.localTD = new TaskDescription();
        this.onClose.emit();
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

    public onCloseSearchUser() {
        this.showSelectUserForm = false;
    }

    uploadFiles(url: string) {
        if (this.primeFileUploader.hasFiles()) {
            this.primeFileUploader.url = url;
            this.primeFileUploader.upload();
        }
    }
}
