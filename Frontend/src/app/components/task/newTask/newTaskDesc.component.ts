import {Component, EventEmitter, Output} from '@angular/core';
import {TaskDescription, TaskStatusType, TaskUser} from "../../../models/task/task.model";
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
    // ↓ Это нужно для работы enum во вью.
    TaskStatusType = TaskStatusType;

    // возвращаем результат
    @Output() onCloseModalNew: EventEmitter<any> = new EventEmitter();

    constructor(private taskService: TaskService,
                private accountService: AccountService) {
    }

    ngOnInit(): void {
        this.localTD = new TaskDescription();
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
            this.taskService.Create(this.localTD).subscribe((res) => {
                    this._show = false;
                    this.onCloseModalNew.emit(this.localTD);
                },
                (error: any) => {
                    console.error("Ошибка" + error);
                });
        }
        else {
            this.taskService.Update(this.localTD).subscribe((res) => {
                    this._show = false;
                },
                (error: any) => {
                    console.error("Ошибка" + error);
                });
        }
    }

    public ChangeStatus(item: TaskUser, status: number) {
        item.status = status;
        this.taskService.AnswerTask(item)
            .subscribe((res) => {
                item.statusName = TaskStatusType[status];
            }, (error: any) => {
                console.error(error);
            });
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
}
