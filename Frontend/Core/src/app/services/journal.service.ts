import { Injectable } from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news/news.model";
import 'rxjs/add/operator/catch';
import {Journal} from "../models/journal/journal.model";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {LessonEvent} from "../models/journal/lessonEvent.model";
import {VisitationConfig} from "../models/journal/visitationConfig.model";
import {catchError, map} from "rxjs/operators";
import {Pair} from "../models/shedule/pair";

@Injectable()
export class JournalService {

   constructor(
       private http: HttpClient,
       private router: Router,
       private handleError: HandelErrorService
   ) {
   }

   public GetAll(): Observable<ResponseStatus> {
      return this.http.get(ApiRouteConstants.Journal.All)
          .pipe(
              map((res: ResponseStatus) => res),
              catchError(e => this.handleError.handle(e))
          );
   }

    public GetJournal(lessonId, month): Observable<ResponseStatus> {
        let params = new HttpParams();
        params = params.set("month", month);

        return this.http.get(ApiRouteConstants.Journal.All
            .replace(":lessonId", lessonId), {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetJournalCertificationReport(lessonId, start, end): Observable<ResponseStatus> {
        let params = new HttpParams();
        params = params.set("start", start);
        params = params.set("end", end);

        return this.http.get(ApiRouteConstants.Journal.СertificationReport
            .replace(":lessonId", lessonId), {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetJournalDates(lessonId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.Dates
            .replace(":lessonId", lessonId))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetEvents(lessonId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.Events
            .replace(":lessonId", lessonId))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Save(journal): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Journal.Save, journal, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public SaveEvent(event: LessonEvent): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Journal.EventSave, event, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public SaveVisitation(config: VisitationConfig): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Journal.VisitationConfigSave, config, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetVisitation(lessonId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.VisitationConfigGet
            .replace(":lessonId", lessonId))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public EventDelete(id: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.EventDelete
            .replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}