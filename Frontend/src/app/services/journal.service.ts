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
          .catch(this.handleError.handle);
   }

    public GetJournal(lessonId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.All
            .replace(":lessonId", lessonId))
            .catch(this.handleError.handle);
    }

    public GetJournalDates(lessonId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.Dates
            .replace(":lessonId", lessonId))
            .catch(this.handleError.handle);
    }

    public GetEvents(lessonId): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.Events
            .replace(":lessonId", lessonId))
            .catch(this.handleError.handle);
    }

    public Save(journal): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Journal.Save, journal, {params: params })
            .catch(this.handleError.handle);
    }

    public SaveEvent(event: LessonEvent): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Journal.EventSave, event, {params: params })
            .catch(this.handleError.handle);
    }

    public EventDelete(id: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Journal.EventDelete
            .replace(":id", id.toString()))
            .catch(this.handleError.handle);
    }
}