import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {ResponseStatus} from "../models/additional/responseStatus";
import {HandelErrorService} from "./handelError.service";
import {UserAccessRight} from "../models/account/access";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs/Observable";
import {StudentJournal, StudentJournalList} from "../models/journal/journal.model";
import {Lesson} from "../models/shedule/lesson";

 
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

    public FindUsersByRoleName(req: string): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.FindUsersByRoleName.replace(":req", req))
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

    public GetProfessorByUser(userId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetProfessorByUser
            .replace(":userId", userId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetStudentByUser(userId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.GetStudentByUser
            .replace(":userId", userId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetUserAccessRight(userId: number): Observable<ResponseStatus> {
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

    public GetStudentByGroup(groupId: number, lessonId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Account.getStudentForGroupAndLesson
            .replace(":groupId", groupId.toString())
            .replace(":lessonId", lessonId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public SaveStudentsSubgroup(studentJournalList: StudentJournalList): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Account.SaveStudentsSubgroup, studentJournalList)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}