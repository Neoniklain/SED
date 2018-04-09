import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {Pair} from "../models/pair.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {Professor} from "../models/professor.model";
import {DepartmentPair} from "../models/departmentPair.model";


@Injectable()
export class DepartmentService{
    constructor(
        private http: HttpClient,
        private router: Router
    ) { }


    public GetChetPairs(): Observable<Array<DepartmentPair>> {
        return this.http.get(ApiRouteConstants.Department.GetChetPairs)
            .catch(this.handleError);
    }

    public GetNechetPairs(): Observable<Array<DepartmentPair>> {
        return this.http.get(ApiRouteConstants.Department.GetNechetPairs)
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