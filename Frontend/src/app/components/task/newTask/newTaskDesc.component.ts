import {Component, EventEmitter, Output} from '@angular/core';
import {TaskDescription, TaskStatusList, Task} from "../../../models/workflow/task.model";
import {TaskService} from "../../../services/task.service";
import {_throw} from "rxjs/observable/throw";
import {AccountService} from "../../../services/accountService";
import {User} from "../../../models/account/user.model";

@Component({
    selector: 'new-task-desc',
    templateUrl: './newTaskDesc.component.html'
})

export class NewTaskDescComponent {
    public localTD: TaskDescription;
    public _foundedUsers: User[];
    public _show: boolean = false;
    public _isCreate: boolean = true;
    public _title: string = '';
    public statuses: TaskStatusList;

    // возвращаем результат
    @Output() onCloseModalNew: EventEmitter<any> = new EventEmitter();

    constructor(private taskService: TaskService,
                private accountService: AccountService) {
    }

    ngOnInit(): void {
        this.localTD = new TaskDescription();
        this.statuses = new TaskStatusList();
    }

    public showDialog(td?: TaskDescription) {
        this.localTD = new TaskDescription();
        this._isCreate = true;
        if (td) {
            this.localTD = td;
            this._title = "Редактирование задачи";
            this._isCreate = false;
        }
        else {
            this._title = "Создание задачи";
        }
        this._show = true;
    }

    public CreateTask() {
        if (this._isCreate) {
            this.taskService.Create(this.localTD).subscribe((res: any) => {
                    this._show = false;
                    console.log('Создано');
                },
                (error: any) => {
                    console.log("Ошибка" + error);
                });
        }
        else {
            this.taskService.Update(this.localTD).subscribe((res: any) => {
                    this._show = false;
                    console.log('Отредактировано');
                },
                (error: any) => {
                    console.log("Ошибка" + error);
                });
        }
    }

    public ChangeStatus(item: Task, status: string) {
        item.status = status;
        this.taskService.AnswerTask(item)
            .subscribe((res: any) => {
                console.log("Success");
            }, (error: any) => {
                console.log(error);
            });
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
}
