import {Component, ViewChild} from '@angular/core';
import {TaskDescription, TaskStatusList} from "../../../models/workflow/task.model";
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
    public statuses: TaskStatusList;

    @ViewChild(NewTaskDescComponent) newTaskDescDialog: NewTaskDescComponent;
    @ViewChild(WorkTaskComponent) workTaskDialog: WorkTaskComponent;

   constructor(private taskService: TaskService,
               private authService: AuthenticationService,
               private router: Router) {
   }

   ngOnInit(): void {
      this.getTaskDescList();
       this.user = new User();
       this.statuses = new TaskStatusList();
       this.authService.getUser().subscribe(
           res => {
               this.user = res;
           },
           error => {
               if (error.statusText === "Forbidden")
                   this.router.navigate(['/404']);
           });
   }

   public isMyTask(item: TaskDescription): boolean {
       for (let i = 0; i < item.subTasks.length; i++) {
           if (item.subTasks[i].executor.id === this.user.id) {
               if ((item.subTasks[i].status !== this.statuses.Completed) &&
                   (item.subTasks[i].status !== this.statuses.Denied))
                   return true;
           }
       }
       return false;
   }

   public getTaskDescList() {
      this.taskService.GetList().subscribe((res: any) => {
              this.taskDescList = res;
              this.checkStatusTaskDescription(this.taskDescList);
          },
          (error: any) => {
              console.log("Ошибка" + error);
          });
   }

   public showDialogNewTaskDescription() {
      this.newTaskDescDialog.showDialog();
   }

    public showDialogEditTaskDescription(td: TaskDescription) {
        this.newTaskDescDialog.showDialog(td);
    }

    public deleteTaskDescription(id: number) {
        this.taskService.Delete(id).subscribe((res: any) => {
                this.getTaskDescList();
            },
            (error: any) => {
                console.log("Ошибка" + error);
            });
    }

    public onCloseModalNew(event: any) {
       console.log("Диалог закрылся!");
       this.getTaskDescList();
    }

    public showDialogTaskWork(td: TaskDescription) {
       let userId = this.user.id;
       let myTask = td.subTasks.filter(function(v) {
            return v.executor.id === userId;
        })[0];
       this.workTaskDialog.showDialog(td, myTask);
    }

    public checkStatusTaskDescription(listTD: TaskDescription[]) {
       for (let tdi = 0; tdi < listTD.length; tdi++) {
          let tempTD = listTD[tdi];
          let count = 0;
          for (let ti = 0; ti < tempTD.subTasks.length; ti++) {
             let tempT = tempTD.subTasks[ti];
             if (tempT.status === "Completed") count++;
             else if (tempT.status === "Denied") count++;
          }
          tempTD.globalStatus = "Processed";
          if (count === tempTD.subTasks.length) tempTD.globalStatus = "Completed";
       }
    }
}
