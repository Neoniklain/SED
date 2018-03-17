import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {Task} from "../../models/workflow/task.model";
import {TaskService} from "../../services/task.service";
import {AuthenticationService} from "../../services/authService";
import {User} from "../../models/account/user.model";
import {AccountService} from "../../services/accountService";
import {FileUploader, FileUploaderOptions} from 'ng2-file-upload';

// upload url
const URL = 'http://localhost:8080/api/excel/ParseStudyPlan';
const MB = 1024 * 1024;

@Component({
   selector: 'task-page',
   templateUrl: './task.component.html',
   styleUrls: ['./task.component.css']
})

export class TaskComponent {
   public displayDialog: boolean = false;
   public displayChangeDialog: boolean = false;
   public displayTaskDialog: boolean = false;
   public tempTask: Task = new Task();
   public taskName: string;
   public taskList: Array<Task>;
   public executors: User[] = [];
   public results: User[] = [];
   public _uploader: FileUploader;

   constructor(private taskService: TaskService,
               private router: Router,
               private accountService: AccountService,
               private authService: AuthenticationService) {
   }

   ngOnInit(): void {
      this.taskList = new Array();
      this.UpdateTaskList();
   }

   CreateTask() {
      this.displayDialog = false;
      let newTask: Task = new Task();
      newTask.collaborators = this.executors;
      newTask.name = this.taskName;
      this.taskService.Create(newTask).subscribe((res: any) => {
             this.UpdateTaskList();
          },
          (error: any) => {
             console.log("Ошибка" + error);
          });
   }

   DeleteTask(id) {
      this.taskService.Delete(id).subscribe((res: any) => {
             this.UpdateTaskList();
          },
          (error: any) => {
             console.log("Ошибка" + error);
          });
   }

   UpdateTaskList() {
      this.taskService.GetList().subscribe((res: any) => {
             this.taskList = res;
          },
          (error: any) => {
             console.log("Ошибка" + error);
          });
   }

   public searchUser(event: any) {
      let query = event.query.substring(0, 60);
      this.accountService.FindUsersByFIO(query)
          .subscribe((res: any) => {
                 this.results = res;
              },
              (error: any) => {
                 console.log("Ошибка" + error);
              });
   }

   public ChangeTask(task: Task) {
      this.displayChangeDialog = true;
      this.tempTask = task;
      this.taskName = this.tempTask.name;
      this.executors = this.tempTask.collaborators;
   }

   public UpdateTask() {
      this.tempTask.name = this.taskName;
      this.tempTask.collaborators = this.executors;
      this.taskService.Update(this.tempTask)
          .subscribe((res: any) => {
                 this.displayChangeDialog = false;
                 this.tempTask = new Task();
              },
              (error: any) => {
                 console.log("Ошибка" + error);
              });
   }

}
