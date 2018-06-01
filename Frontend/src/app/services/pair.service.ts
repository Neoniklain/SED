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
export class PairService {

    constructor(private http: HttpClient,
                private router: Router,
                private handleError: HandelErrorService) {
    }

    public Get(id: number): Observable<ResponseStatus> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Pair.Get.replace(":id", id.toString()))
            .catch(this.handleError.handle);
    }


    public Save(pair: Pair): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Pair.Save, pair, {params: params })
            .catch(this.handleError.handle);
    }

    public Delete(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Pair.Delete.replace(":id", id.toString())).catch(this.handleError.handle);
    }

    GetPeofessorPair(professorId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Pair.Proffesor.replace(":id", professorId.toString()))
            .catch(this.handleError.handle);
    }

    GetDepartmentPair(departmentId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Pair.Department.replace(":id", departmentId.toString()))
            .catch(this.handleError.handle);
    }

    GetGroupPair(groupId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Pair.Group.replace(":id", groupId.toString()))
            .catch(this.handleError.handle);
    }
}
