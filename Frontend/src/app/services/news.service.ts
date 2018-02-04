import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news.model";
import 'rxjs/add/operator/catch';

@Injectable()
export class NewsService {

    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

    public GetAll(): Observable<Array<News>> {
        return this.http.get(ApiRouteConstants.News.All)
            .catch(this.handleError);
    }

    public Get(id: number) {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.News.Get.replace(":id", id.toString()))
            .catch(this.handleError);
    }

    public Delete(id: number) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.News.Delete.replace(":id", id.toString()), null, {
            responseType: "text",
            params: params
        }).catch(this.handleError);
    }

    public GetLast() {
        return this.http.get(ApiRouteConstants.News.Last)
            .catch(this.handleError);
    }

    public Save(news: News) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.News.Save, news, {params: params })
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