import {Component} from '@angular/core';
import {TaskDescription, TaskStatusType} from "../../../models/task/task.model";
import {TaskService} from "../../../services/task.service";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/auth.service";
import {Router} from "@angular/router";
import {NotificationService} from "../../../services/notification.service";
import {AccessRightType} from "../../../models/account/access";
import {Globals} from "../../../globals";
import {StatusType} from "../../../models/statusType.model";

@Component({
   selector: 'task-list',
   templateUrl: './taskDescList.component.html'
})

export class TaskDescListComponent {
    public listTaskDescCreator: TaskDescription[];
    public listTaskDescExecutor: TaskDescription[];
    public user: User;
    public loading: boolean = true;
    public viewName: string;
    public localTD: TaskDescription;
    // ↓ true - показать созданные / false - показать назначенные юзеру
    public showCreated: boolean = false;
    // ↓ Нужно для работы на view
    TaskStatusType = TaskStatusType;
    AccessRightType = AccessRightType;

    constructor(private taskService: TaskService,
                private authService: AuthenticationService,
                private router: Router,
                private globals: Globals,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.viewName = 'list';
        this.listTaskDescCreator = [];
        this.listTaskDescExecutor = [];
        this.user = new User();
        this.globals.getUser.subscribe(
            result => {
                this.user = result;
                this.getTDCreator();
                this.getTDExecutor();
            }
        );
    }

    public getStatusName(statusId: number) {
        return TaskStatusType[statusId];
    }

    private correctingDate() {
        for (let task of this.listTaskDescExecutor) {
            task.dateCreate = new Date(task.dateCreate);
            if (task.dateRequired != null) {
                task.dateRequired = new Date(task.dateRequired);
            }
        }
        for (let task of this.listTaskDescCreator) {
            task.dateCreate = new Date(task.dateCreate);
            if (task.dateRequired != null) {
                task.dateRequired = new Date(task.dateRequired);
            }
        }
    }

    public getTDExecutor() {
        this.loading = true;
        this.taskService.GetListExecutor().subscribe((res) => {
                this.loading = false;
                this.listTaskDescExecutor = res.data;
                this.correctingDate();
            },
            (error) => {
                console.error("Ошибка" + error);
            });
    }

    public getTDCreator() {
        this.loading = true;
        this.taskService.GetListCreator().subscribe((res) => {
                this.loading = false;
                this.listTaskDescCreator = res.data;
                this.correctingDate();
            },
            (error: any) => {
                console.error("Ошибка" + error);
            });
    }

    public deleteTask(item: TaskDescription) {
        this.taskService.DeleteTask(item.id)
            .subscribe(
                res => {
                    this.notificationService.FromStatus(res);
                    if (res.status == StatusType[StatusType.OK]) {
                        this.listTaskDescCreator.splice(this.listTaskDescCreator.findIndex(function (x) {
                            return x.id == item.id;
                        }), 1);
                    }
                },
                error => {
                    console.error(error);
                }
            );
    }

    public setComp(name: string) {
        this.viewName = name;
        if (name == 'list') {
            this.localTD = new TaskDescription();
        }
    }

    public letShow(name: string): boolean {
        if (name == this.viewName) {
            return true;
        }
        return false;
    }

    public showDetails(item: TaskDescription) {
        this.localTD = item;
        this.setComp('detail');
    }

    public showDialogNewTask() {
        this.setComp('new');
    }

    public onCreateNew() {
        this.getTDCreator();
    }

    public onReturnDetail() {
        this.getTDExecutor();
        this.getTDCreator();
        this.setComp('list');
    }
}
