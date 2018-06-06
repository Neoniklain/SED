import {Component, EventEmitter, Output} from '@angular/core';
import {TaskDescription, TaskUser, TaskStatusType} from "../../../models/task/task.model";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {User} from "../../../models/account/user.model";

@Component({
    selector: 'work-task',
    templateUrl: './workTask.component.html'
})
export class WorkTaskComponent {
    public localTD: TaskDescription;
    public _task: TaskUser;
    public _foundedUsers: User[];
    public _show: boolean = false;
    public _title: string = '';
    public _editable: boolean = false;
    //public statuses: TaskStatusList;

    // возвращаем результат
    //@Output() onCloseModalWork: EventEmitter<any> = new EventEmitter();

    constructor(private taskService: TaskService,
                private accountService: AccountService) {
    }

    ngOnInit(): void {
        this._task = new TaskUser();
        this.localTD = new TaskDescription();
        this._editable = false;
        //this.statuses = new TaskStatusList();
    }

    public showDialog(td: TaskDescription, task: TaskUser) {
        if ((task.status == TaskStatusType.Processed) ||
            (task.status == TaskStatusType.SentToRevision) ||
            (task.status == TaskStatusType.Viewed)){
            this._editable = true;
        }
        this.localTD = new TaskDescription();
        this._task = new TaskUser();
        this._title = "Выполнение задачи";
        this.localTD = td;
        this._task = task;
        this._show = true;
    }

    public searchUser(event: any) {
        let query = event.query.substring(0, 60);
        this.accountService.FindUsersByFIO(query)
            .subscribe((res) => {
                    this._foundedUsers = res.data;
                },
                (error: any) => {
                    console.error("Ошибка" + error);
                });
    }

    public AnswerTask() {
        this._task.status = TaskStatusType.SentToReview;
        this.taskService.AnswerTask(this._task)
            .subscribe((res) => {
                this._show = false;
                this._task.statusName = TaskStatusType[TaskStatusType.SentToReview];
            }, (error: any) => {
                console.error(error);
                this._show = false;
            });
    }
}
