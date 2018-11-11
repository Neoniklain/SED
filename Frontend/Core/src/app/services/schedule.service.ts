import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import 'rxjs/add/operator/catch';
import {Pair} from "../models/shedule/pair";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";

@Injectable()
export class ScheduleService {

    constructor(private http: HttpClient,
                private router: Router,
                private handleError: HandelErrorService) {
    }

    public Get(id: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.Get.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Save(pair: Pair, skipWarnings?: boolean): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Schedule.Save, {pairModel: pair, skipWarnings: skipWarnings ? skipWarnings : false}, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Delete(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Schedule.Delete.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    GetPeofessorPair(professorId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.ProffesorPairs.replace(":id", professorId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    GetLessonPair(lessonId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.LessonPairs.replace(":id", lessonId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    GetDepartmentPair(departmentId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.DepartmentPairs.replace(":id", departmentId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    GetGroupPair(groupId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.GroupPairs.replace(":id", groupId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    GetProfessorLessons(professorId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.ProffesorLessons.replace(":id", professorId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    GetGroupLessons(groupId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.GroupLessons.replace(":id", groupId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

}












