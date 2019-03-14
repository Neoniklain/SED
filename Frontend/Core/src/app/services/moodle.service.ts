import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TaskUser, TaskDescription} from "../models/task/task.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";
import {Dictionary} from "../models/admin/dictionary.model";

@Injectable()
export class  MoodleService {

    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public CreateUser(userId): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.get(ApiRouteConstants.Moodle.Create.User.replace(":userId", userId.toString()), {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public CreateStudentsOfGroup(groupId): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.get(ApiRouteConstants.Moodle.Create.Students.replace(":groupId", groupId.toString()), {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}
