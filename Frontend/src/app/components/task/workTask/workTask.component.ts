import {Component, EventEmitter, Output} from '@angular/core';
import {TaskDescription, Task, TaskStatusList} from "../../../models/workflow/task.model";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {User} from "../../../models/account/user.model";

@Component({
    selector: 'work-task',
    templateUrl: './workTask.component.html'
})
export class WorkTaskComponent {
    public localTD: TaskDescription;
    public _task: Task;
    public _foundedUsers: User[];
    public _show: boolean = false;
    public _title: string = '';
    public statuses: TaskStatusList;

    // возвращаем результат
    //@Output() onCloseModalWork: EventEmitter<any> = new EventEmitter();

    constructor(private taskService: TaskService,
                private accountService: AccountService) {
    }

    ngOnInit(): void {
        this._task = new Task();
        this.localTD = new TaskDescription();
        this.statuses = new TaskStatusList();
    }

    public showDialog(td: TaskDescription, task: Task) {
        this.localTD = new TaskDescription();
        this._task = new Task();
        this._title = "Выполнение задачи";
        this.localTD = td;
        this._task = task;
        this._show = true;
    }

    public searchUser(event: any) {
        let query = event.query.substring(0, 60);
        this.accountService.FindUsersByFIO(query)
            .subscribe((res: any) => {
                    this._foundedUsers = res;
                    console.log(this._foundedUsers);
                },
                (error: any) => {
                    console.log("Ошибка" + error);
                });
    }

    public AnswerTask() {
        this._task.status = this.statuses.SentToReview;
        this.taskService.AnswerTask(this._task)
            .subscribe((res: any) => {
                console.log("Success");
                this._show = false;
            }, (error: any) => {
                console.log(error);
                this._show = false;
            });
    }
}
