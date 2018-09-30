import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from "@angular/core";
import {TaskService} from "../../../services/task.service";
import {AccountService} from "../../../services/accountService";
import {NotificationService} from "../../../services/notification.service";
import {FileService} from "../../../services/file.service";
import {AuthenticationService} from "../../../services/authService";
import {TaskDescription, TaskStatusType, TaskType, TaskUser} from "../../../models/task/task.model";
import {ObjectType} from "../../../models/file/file.model";
import {StatusType} from "../../../models/statusType.model";
import {FileUpload} from "primeng/primeng";
import {ApiRouteConstants, BaseApiUrl} from "../../../bootstrap/app.route.constants";

@Component({
    selector: 'work-task',
    templateUrl: './workTask.component.html'
})
export class WorkTaskComponent implements OnInit {
    @Input()
    localTD: TaskDescription;
    @Output()
    onClose: EventEmitter<any> = new EventEmitter();
    @Output()
    onAnswer: EventEmitter<any> = new EventEmitter();
    @ViewChild(FileUpload)
    primeFileUploader: FileUpload;

    public myTaskUser: TaskUser;
    public isShowFiles: boolean = false;
    public isNeedAnswer: boolean = false;

    constructor(private taskService: TaskService,
                private accountService: AccountService,
                private notificationService: NotificationService,
                private authService: AuthenticationService,
                private fileService: FileService) {
    }

    ngOnInit(): void {
        this.myTaskUser = new TaskUser();
        if (this.localTD == null) {
            this.localTD = new TaskDescription();
            this.localTD.name = "Задача не существует";
        }
        else {
            this.checkTaskUser();
            this.CheckIsNeedAnswer();
        }
    }

    private checkTaskUser() {
        this.taskService.GetTaskUserByTD(this.localTD.id, true)
            .subscribe(
                res => {
                    if (res.status == StatusType[StatusType.OK]) {
                        this.localTD.taskUsers = [];
                        this.localTD.taskUsers = res.data;
                        this.myTaskUser = this.localTD.taskUsers[0];
                        this.checkFilesTU();
                    }
                    else {
                        this.localTD.taskUsers = [];
                        this.notificationService.FromStatus(res);
                    }
                },
                error => {
                    this.localTD.taskUsers = [];
                    console.error(error);
                }
            );
    }

    public AnswerTask() {
        this.taskService.AnswerTask(this.myTaskUser)
            .subscribe(
                res => {
                    this.notificationService.FromStatus(res);
                    if (res.status == StatusType[StatusType.OK]) {
                        let url = BaseApiUrl + ApiRouteConstants.File.AddFileForObject
                            .replace(":objectTypeId", ObjectType.TaskUser.toString())
                            .replace(":objectId", res.data.id.toString());
                        this.uploadFiles(url);
                        this.onAnswer.emit(res.data);
                    }
                },
                error => {
                    console.log(error);
                }
            );
    }

    public CloseDialog() {
        this.onClose.emit();
    }

    public CheckIsNeedAnswer() {
        this.isNeedAnswer = false;
        if (this.localTD.status != TaskStatusType.Completed) {
            if (this.myTaskUser != null) {
                if (this.myTaskUser.status == TaskStatusType.Processed) {
                    this.isNeedAnswer = true;
                }
                if (this.myTaskUser.status == TaskStatusType.SentToRevision) {
                    this.isNeedAnswer = true;
                }
            }
        }
    }

    uploadFiles(url: string) {
        if (this.primeFileUploader.hasFiles()) {
            this.primeFileUploader.url = url;
            this.primeFileUploader.upload();
        }
    }

    private checkFilesTU() {
        if (this.myTaskUser != null) {
            this.fileService.getFilesForObject(ObjectType.TaskUser, this.myTaskUser.id)
                .subscribe(
                    res => {
                        if (res.status == StatusType[StatusType.OK]) {
                            this.myTaskUser.files = res.data;
                            if (this.myTaskUser.files != null) {
                                if (this.myTaskUser.files.length > 0) {
                                    this.isShowFiles = true;
                                }
                            }
                            else {
                                this.isShowFiles = false;
                            }
                        }
                        else {
                            this.isShowFiles = false;
                        }
                    },
                    error => {
                        console.error(error);
                        this.isShowFiles = false;
                    }
                );
        }
    }
}