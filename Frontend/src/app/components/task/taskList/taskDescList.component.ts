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
    // ↓ true - показать созданные / false - показать назначенные юзеру
    public showCreated: boolean = false;
    // ↓ Нужно для работы на view
    TaskStatusType = TaskStatusType;
    AccessRightType = AccessRightType;


    @ViewChild(NewTaskDescComponent)
    newTaskDescDialog: NewTaskDescComponent;

    @ViewChild(WorkTaskComponent)
    workTaskDialog: WorkTaskComponent;

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

    public switchList(page: boolean) {
        this.showCreated = page;
    }

    public showDialogNewTask() {
        this.newTaskDescDialog.showDialog();
    }

    public onCreateNew() {
        this.getTDCreator();
    }

    public showDetailsDialog(item: TaskDescription) {

    }

    public showWorkDialog(item: TaskDescription) {

    }
}
