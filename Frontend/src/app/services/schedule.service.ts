import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import 'rxjs/add/operator/catch';
import {Pair} from "../models/shedule/pair";
import {DepartmentShedule} from "../models/shedule/departmentShedule";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";

@Injectable()
export class ScheduleService {

    constructor(private http: HttpClient,
                private router: Router,
                private handleError: HandelErrorService) {
    }

    public Get(id: number): Observable<ResponseStatus> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Schedule.Get.replace(":id", id.toString()))
            .catch(this.handleError.handle);
    }


    public Save(pair: Pair): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Schedule.Save, pair, {params: params })
            .catch(this.handleError.handle);
    }

    public Delete(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Schedule.Delete.replace(":id", id.toString())).catch(this.handleError.handle);
    }

    GetPeofessorPair(professorId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.ProffesorPairs.replace(":id", professorId.toString()))
            .catch(this.handleError.handle);
    }

    GetLessonPair(lessonId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.LessonPairs.replace(":id", lessonId.toString()))
            .catch(this.handleError.handle);
    }


    GetDepartmentPair(departmentId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.DepartmentPairs.replace(":id", departmentId.toString()))
            .catch(this.handleError.handle);
    }

    GetGroupPair(groupId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.GroupPairs.replace(":id", groupId.toString()))
            .catch(this.handleError.handle);
    }

    GetProfessorLessons(professorId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.ProffesorLessons.replace(":id", professorId.toString()))
            .catch(this.handleError.handle);
    }

}













