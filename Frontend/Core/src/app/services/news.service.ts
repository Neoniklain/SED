import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news/news.model";
import 'rxjs/add/operator/catch';
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";

@Injectable()
export class NewsService {


    constructor(
        private http: HttpClient,
        private router: Router,
        private handleError: HandelErrorService
    ) {
    }

    public GetAll(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.News.All)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Get(id: number): Observable<ResponseStatus> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.News.Get.replace(":id", id.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Delete(id: number): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.News.Delete.replace(":id", id.toString()), null, {
            params: params
        }).pipe(
            map((res: ResponseStatus) => res),
            catchError(e => this.handleError.handle(e))
        );
    }

    public GetLast(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.News.Last)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Save(news: News): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.News.Save, news, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}