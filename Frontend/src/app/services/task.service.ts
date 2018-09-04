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

    public Get(id: number) : Observable<ResponseStatus>{
      let params: HttpParams = new HttpParams();
      return this.http.get(ApiRouteConstants.Task.Get.replace(":id", id.toString()))
          .pipe(
              map((res: ResponseStatus) => res),
              catchError(e => this.handleError.handle(e))
          );
    }

    public Delete(id: number) : Observable<ResponseStatus>{
      return this.http.get(ApiRouteConstants.Task.Delete.replace(":id", id.toString()))
          .pipe(
              map((res: ResponseStatus) => res),
              catchError(e => this.handleError.handle(e))
          );
    }

    public GetList() : Observable<ResponseStatus>{
    return this.http.get(ApiRouteConstants.Task.All)
        .pipe(
            map((res: ResponseStatus) => res),
            catchError(e => this.handleError.handle(e))
        );
    }

    public Update(item: TaskDescription) : Observable<ResponseStatus>{
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Update, item, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Create(item: TaskDescription) : Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Create, item, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public AnswerTask(item: TaskUser) : Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.AnswerTask, item, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public ChangeStatus(tu_id: number, status_id: number) : Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.ChangeStatus + "/" + tu_id + "/" + status_id, null, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}
