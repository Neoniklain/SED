import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import 'rxjs/add/operator/catch';
import {Pair} from "../models/shedule/pair";
import {DepartmentShedule} from "../models/shedule/departmentShedule";

@Injectable()
export class PairService {

    constructor(private http: HttpClient,
                private router: Router) {
    }

    public Get(id: number) {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Pair.Get.replace(":id", id.toString()))
            .catch(this.handleError);
    }


    public Save(pair: Pair) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Pair.Save, pair, {params: params })
            .catch(this.handleError);
    }

    public Delete(id: number) {
        return this.http.delete(ApiRouteConstants.Pair.Delete.replace(":id", id.toString())).catch(this.handleError);
    }

    GetPeofessorPair(professorId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Proffesor.Get.replace(":id", professorId.toString()))
            .catch(this.handleError);
    }
    GetPeofessorPairOdd(professorId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Proffesor.Odd.replace(":id", professorId.toString()))
            .catch(this.handleError);
    }
    GetPeofessorPairEven(professorId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Proffesor.Even.replace(":id", professorId.toString()))
            .catch(this.handleError);
    }

    GetDepartmentPair(departmentId: number): Observable<Array<DepartmentShedule>> {
        return this.http.get(ApiRouteConstants.Pair.Department.Get.replace(":id", departmentId.toString()))
            .catch(this.handleError);
    }
    GetDepartmentPairOdd(departmentId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Department.Odd.replace(":id", departmentId.toString()))
            .catch(this.handleError);
    }
    GetDepartmentPairEven(departmentId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Department.Even.replace(":id", departmentId.toString()))
            .catch(this.handleError);
    }

    GetGroupPair(groupId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Group.Get.replace(":id", groupId.toString()))
            .catch(this.handleError);
    }
    GetGroupPairOdd(groupId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Group.Odd.replace(":id", groupId.toString()))
            .catch(this.handleError);
    }
    GetGroupPairEven(groupId: number): Observable<Array<Pair>> {
        return this.http.get(ApiRouteConstants.Pair.Group.Even.replace(":id", groupId.toString()))
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
