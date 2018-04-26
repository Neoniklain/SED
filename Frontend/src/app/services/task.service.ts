import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {Task, TaskDescription} from "../models/workflow/task.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";

@Injectable()
export class  TaskService {
    constructor(private http: HttpClient) { }

    public Get(id: number) {
      let params: HttpParams = new HttpParams();
      return this.http.get(ApiRouteConstants.Task.Get.replace(":id", id.toString()))
          .catch(this.handleError);
    }

    public Delete(id: number) {
      return this.http.get(ApiRouteConstants.Task.Delete.replace(":id", id.toString()))
          .catch(this.handleError);
    }

    public GetList(): Observable<Array<TaskDescription>> {
    return this.http.get(ApiRouteConstants.Task.All)
        .catch(this.handleError);
    }

    public Update(item: TaskDescription) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Update, item, {params: params })
        .catch(this.handleError);
    }

    public Create(item: TaskDescription) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Create, item, {params: params })
        .catch(this.handleError);
    }

    public AnswerTask(item: Task) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.AnswerTask, item, {params: params })
            .catch(this.handleError);
    }

    private handleError(error: HttpErrorResponse | any) {
        let errMsg: string;
        if (error instanceof HttpErrorResponse) {
            const body = error.error || "";
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ""} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}
