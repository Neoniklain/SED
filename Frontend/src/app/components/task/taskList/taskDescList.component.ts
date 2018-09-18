import {Component, ViewChild} from '@angular/core';
import {TaskDescription, TaskStatusType} from "../../../models/task/task.model";
import {TaskService} from "../../../services/task.service";
import {forEach} from "@angular/router/src/utils/collection";
import {NewTaskDescComponent} from "../newTask/newTaskDesc.component";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {Router} from "@angular/router";
import {WorkTaskComponent} from "../workTask/workTask.component";
import {NotificationService} from "../../../services/notification.service";
import {AccessRightType} from "../../../models/account/access";
import {Globals} from "../../../globals";
import {ResponseStatus} from "../../../models/additional/responseStatus";
import {DetailTaskComponent} from "../detailTask/detailTask.component";
import {StatusType} from "../../../models/statusType.model";

@Component({
   selector: 'task-list',
   templateUrl: './taskDescList.component.html',
   styleUrls: ['./taskDescList.component.css']
})

export class TaskDescListComponent {
    public listTaskDescCreator: TaskDescription[];
    public listTaskDescExecutor: TaskDescription[];
    public user: User;
    public loading: boolean = true;
    public isCreated: boolean = false;
    // ↓ true - показать созданные / false - показать назначенные юзеру
    public showCreated: boolean = false;
    // ↓ Нужно для работы на view
    TaskStatusType = TaskStatusType;
    AccessRightType = AccessRightType;


    @ViewChild(NewTaskDescComponent)
    newTaskDialog: NewTaskDescComponent;

    @ViewChild(WorkTaskComponent)
    workTaskDialog: WorkTaskComponent;

    @ViewChild(DetailTaskComponent)
    detailTaskDialog: DetailTaskComponent;

    constructor(private taskService: TaskService,
                private authService: AuthenticationService,
                private router: Router,
                private globals: Globals,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
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

    public getTDExecutor() {
        this.loading = true;
        this.taskService.GetListExecutor().subscribe((res) => {
                this.loading = false;
                this.listTaskDescExecutor = res.data;
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
            },
            (error: any) => {
                console.error("Ошибка" + error);
            });
    }

    public showDialogNewTask() {
        this.isCreated = true;
        this.newTaskDialog.showDialog();
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

    public onCreateNew() {
        this.getTDCreator();
    }

    public showDetailsDialog(item: TaskDescription) {
        this.detailTaskDialog.Show(item);
    }

    public showWorkDialog(item: TaskDescription) {

    }

    public letShow(comp: number): boolean {
        // Список задач
        if (comp == 1) {
            if (!this.isCreated) {
                return true;
            }
        }
        // Добавление новой задачи
        if (comp == 2) {
            if (this.isCreated) {
                return true;
            }
        }
        if (comp == 3) {
            return true;
        }
        return false;
    }

    public onCloseNewDialog() {
        this.isCreated = false;
    }
}
