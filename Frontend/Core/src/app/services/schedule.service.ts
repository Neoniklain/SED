import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import 'rxjs/add/operator/catch';
import {Pair} from "../models/shedule/pair";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";
import {EducationPeriod} from "../models/educationPeriod";
import {SemesterNumberYear} from "../models/semesterNumberYear.model";

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
        return this.http.post(ApiRouteConstants.Schedule.Save, {
            pairModel: pair,
            skipWarnings: skipWarnings ? skipWarnings : false
        }, {params: params})
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

    public GetProfessorPair(professorId: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.ProffesorPairs
            .replace(":id", professorId.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetDepartmentPair(departmentId: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.DepartmentPairs
            .replace(":id", departmentId.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetGroupPair(groupId: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.GroupPairs
            .replace(":id", groupId.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetLessonPair(lessonId: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.LessonPairs.replace(":id", lessonId.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetProfessorLessons(professorId: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.ProffesorLessons.replace(":id", professorId.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetGroupLessons(groupId: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.GroupLessons.replace(":id", groupId.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetPrityWeek(semesterNumberYear: SemesterNumberYear): Observable<number> {
        return this.http.get(ApiRouteConstants.Schedule.PrityWeek
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res.data == 0 ? 0 : 1),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetEducationPeriod(id: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.EducationPeriod.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public getEducationPeriodForGroup(id: number, semesterNumberYear: SemesterNumberYear): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Schedule.EducationPeriodForGroup
            .replace(":id", id.toString())
            .replace(":year", semesterNumberYear.year.toString())
            .replace(":semester", semesterNumberYear.semester.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public SavePeriod(educationPeriod: EducationPeriod): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Schedule.SavePeriod, educationPeriod, {params: params})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

}












