import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {ResponseStatus} from "../models/additional/responseStatus";
import {HandelErrorService} from "./handelError.service";
import {UserAccessRight} from "../models/account/access";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs/Observable";

 
@Injectable()
export class AccountService {

    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public FindUsersByFIO(req: string): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.FindUsersByFIO.replace(":req", req))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public FindUserByLogin(req: string): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.FindUserByUsername.replace(":req", req))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public setProfessorDepartment(userId: number, departmentId: number): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Account.SetProfessorDepartment
                .replace(":userId", userId.toString())
                .replace(":departmentId", departmentId.toString()),
            null)
            .pipe(
            map((res: ResponseStatus) => res),
            catchError(e => this.handleError.handle(e))
        );
    }

    public setStudentGroup(userId: number, groupId: number): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Account.SetStudentGroup
            .replace(":userId", userId.toString())
            .replace(":groupId", groupId.toString()),
            null).pipe(
            map((res: ResponseStatus) => res),
            catchError(e => this.handleError.handle(e))
        );
    }

    public GetProfessors(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetProfessors)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetProfessorByUser(userId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetProfessorByUser
            .replace(":userId", userId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetStudentByUser(userId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetStudentByUser
            .replace(":userId", userId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetUserAccessRight(userId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetUserAccessRight
            .replace(":userId", userId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public SaveUserAccessRight(acceses: UserAccessRight): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Account.SaveUserAccessRight, acceses)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}