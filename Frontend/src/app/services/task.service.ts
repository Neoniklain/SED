import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TaskUser, TaskDescription} from "../models/task/task.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";

@Injectable()
export class  TaskService {

    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public Get(id: number) {
      let params: HttpParams = new HttpParams();
      return this.http.get(ApiRouteConstants.Task.Get.replace(":id", id.toString()))
          .catch(this.handleError.handle);
    }

    public Delete(id: number) {
      return this.http.get(ApiRouteConstants.Task.Delete.replace(":id", id.toString()))
          .catch(this.handleError.handle);
    }

    public GetList(): Observable<Array<TaskDescription>> {
    return this.http.get(ApiRouteConstants.Task.All)
        .catch(this.handleError.handle);
    }

    public Update(item: TaskDescription) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Update, item, {params: params })
        .catch(this.handleError.handle);
    }

    public Create(item: TaskDescription) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.Create, item, {params: params })
        .catch(this.handleError.handle);
    }

    public AnswerTask(item: TaskUser) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Task.AnswerTask, item, {params: params })
            .catch(this.handleError.handle);
    }
}
