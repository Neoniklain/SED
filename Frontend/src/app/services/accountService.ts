import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ApiRouteConstants, RouteConstants} from "../bootstrap/app.route.constants";
import {Professor} from "../models/professor";

 
@Injectable()
export class AccountService {

    constructor(private http: HttpClient) { }

    public FindUserByName(req: string) {
        return this.http.get(ApiRouteConstants.Account.FindUsersByName.replace(":req", req), {responseType: "text"})
            .catch(this.handleError);
    }

    public setProfessorDepartment(userId: number, departmentId: number) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Account.SetProfessorDepartment
                .replace(":userId", userId.toString())
                .replace(":departmentId", departmentId.toString()),
            null, params ).catch(this.handleError);
    }

    public setStudentGroup(userId: number, groupId: number) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Account.SetStudentGroup
            .replace(":userId", userId.toString())
            .replace(":groupId", groupId.toString()),
            null, params ).catch(this.handleError);
    }

    public GetProfessors(): Observable<Array<Professor>> {
        return this.http.get(ApiRouteConstants.Account.GetProfessors)
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