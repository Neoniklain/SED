import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TaskUser, TaskDescription} from "../models/task/task.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";

@Injectable()
export class  TaskService {

    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public GetListAll(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Task.ListAll)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetListCreator(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Task.ListCreator)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetListExecutor(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Task.ListExecutor)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Create(item: TaskDescription): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Create, item, {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public AnswerTask(item: TaskUser): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.AnswerTask, item, {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public ChangeStatusTaskDesc(td_id: number, status_id: number): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.ChangeStatusTaskDesc + "/" + td_id + "/" + status_id, null, {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public ChangeStatusTaskUser(tu_id: number, status_id: number): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.ChangeStatusTaskUser + "/" + tu_id + "/" + status_id, null, {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetTaskDesc(id: number): Observable<ResponseStatus> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Task.GetTaskDesc.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetTaskUser(id: number): Observable<ResponseStatus> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Task.GetTaskUser.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public UpdateTaskDesc(item: TaskDescription): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.UpdateTaskDesc, item, {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public DeleteTask(id: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Task.DeleteTask.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public DeleteTaskUser(id: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Task.DeleteTaskUser.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}
