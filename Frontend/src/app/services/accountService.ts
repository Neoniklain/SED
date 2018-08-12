import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ApiRouteConstants, RouteConstants} from "../bootstrap/app.route.constants";
import {Professor} from "../models/account/professor";
import {ResponseStatus} from "../models/additional/responseStatus";
import {HandelErrorService} from "./handelError.service";
import {UserAccessRight} from "../models/account/access";

 
@Injectable()
export class AccountService {

    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public FindUsersByFIO(req: string): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.FindUsersByFIO.replace(":req", req))
            .catch(this.handleError.handle);
    }

    public FindUserByLogin(req: string): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.FindUserByUsername.replace(":req", req))
            .catch(this.handleError.handle);
    }

    public setProfessorDepartment(userId: number, departmentId: number): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Account.SetProfessorDepartment
                .replace(":userId", userId.toString())
                .replace(":departmentId", departmentId.toString()),
            null, params ).catch(this.handleError.handle);
    }

    public setStudentGroup(userId: number, groupId: number): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Account.SetStudentGroup
            .replace(":userId", userId.toString())
            .replace(":groupId", groupId.toString()),
            null, params ).catch(this.handleError.handle);
    }

    public GetProfessors(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetProfessors)
            .catch(this.handleError.handle);
    }

    public GetProfessorByUser(userId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetProfessorByUser
            .replace(":userId", userId.toString()))
            .catch(this.handleError.handle);
    }

    public GetStudentByUser(userId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetStudentByUser
            .replace(":userId", userId.toString()))
            .catch(this.handleError.handle);
    }

    public GetUserAccessRight(userId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetUserAccessRight
            .replace(":userId", userId.toString()))
            .catch(this.handleError.handle);
    }

    public SaveUserAccessRight(acceses: UserAccessRight): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Account.SaveUserAccessRight, acceses)
            .catch(this.handleError.handle);
    }
}