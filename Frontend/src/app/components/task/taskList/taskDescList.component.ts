import {Component, ViewChild} from '@angular/core';
import {TaskDescription, TaskStatusType} from "../../../models/workflow/task.model";
import {TaskService} from "../../../services/task.service";
import {forEach} from "@angular/router/src/utils/collection";
import {NewTaskDescComponent} from "../newTask/newTaskDesc.component";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {Router} from "@angular/router";
import {WorkTaskComponent} from "../workTask/workTask.component";

@Component({
   selector: 'task-list',
   templateUrl: './taskDescList.component.html',
   styleUrls: ['./taskDescList.component.css']
})

export class TaskDescListComponent {
    public taskDescList: TaskDescription[] = [];
    public user: User;
    // ↓ Нужно для работы на view
    TaskStatusType = TaskStatusType;

    @ViewChild(NewTaskDescComponent) newTaskDescDialog: NewTaskDescComponent;
    @ViewChild(WorkTaskComponent) workTaskDialog: WorkTaskComponent;

   constructor(private taskService: TaskService,
               private authService: AuthenticationService,
               private router: Router) {
   }

   ngOnInit(): void {
      this.getTaskDescList();
       this.user = new User();
       //this.statuses = new TaskStatusList();
       this.authService.getUser().subscribe(
           res => {
               this.user = res.data;
           },
           error => {
               if (error.statusText === "Forbidden")
                   this.router.navigate(['/404']);
           });
   }

   public isMyTask(item: TaskDescription): boolean {
       let localUser = this.user;
       var result = item.subTasks.filter(function(v) {
           return v.executor.id === localUser.id;
       })[0];

       if(result != null){
           if ((result.status == TaskStatusType.Processed) ||
               (result.status == TaskStatusType.SentToRevision) ||
               (result.status == TaskStatusType.Viewed))
           {
               return true;
           }
       }

       return false;
   }

   public getTaskDescList() {
      this.taskService.GetList().subscribe((res) => {
              this.taskDescList = res;
              this.checkStatusTaskDescription(this.taskDescList);
          },
          (error: any) => {
              console.error("Ошибка" + error);
          });
   }

   public showDialogNewTaskDescription() {
      this.newTaskDescDialog.showDialog();
   }

    public showDialogEditTaskDescription(td: TaskDescription) {
        this.newTaskDescDialog.showDialog(td);
    }

    public deleteTaskDescription(id: number) {
        this.taskService.Delete(id).subscribe((res) => {
                this.getTaskDescList();
            },
            (error: any) => {
                console.error("Ошибка" + error);
            });
    }

    public onCloseModalNew(TD: TaskDescription) {
       this.getTaskDescList();
    }

    public showDialogTaskWork(td: TaskDescription) {
       let userId = this.user.id;
       let myTask = td.subTasks.filter(function(v) {
            return v.executor.id === userId;
        })[0];
       if(myTask != null){
           this.workTaskDialog.showDialog(td, myTask);
       }
    }

    public checkStatusTaskDescription(listTD: TaskDescription[]) {
       for (let tdi = 0; tdi < listTD.length; tdi++) {
          let tempTD = listTD[tdi];
          let count = 0;
          for (let ti = 0; ti < tempTD.subTasks.length; ti++) {
             let tempT = tempTD.subTasks[ti];
             if (tempT.status === TaskStatusType.Completed) count++;
             else if (tempT.status === TaskStatusType.Denied) count++;
          }
          tempTD.statusName = TaskStatusType[TaskStatusType.Processed];
          if (count === tempTD.subTasks.length) tempTD.statusName = TaskStatusType[TaskStatusType.Completed];
       }
    }
}
