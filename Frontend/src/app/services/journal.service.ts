import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news.model";
import 'rxjs/add/operator/catch';
import {Journal} from "../models/journal/journal.model";

@Injectable()
export class JournalService {

   constructor(
       private http: HttpClient,
       private router: Router
   ) { }

   public GetAll(): Observable<Journal> {
      return this.http.get(ApiRouteConstants.Journal.All)
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