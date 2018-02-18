import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news.model";
import 'rxjs/add/operator/catch';
import {User} from "../models/user.model";
import {Discipline} from "../models/Discipline.model";
import {LazyLoadEvent} from "primeng/api";

@Injectable()
export class AdminService {

    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

    public GetUsers(filterQuery: LazyLoadEvent): Observable<Array<User>> {
       let params = new HttpParams();
       let filter = {
          rows: filterQuery ? filterQuery.rows : 0,
          first: filterQuery ? filterQuery.first : 0
       }
        return this.http.post(ApiRouteConstants.Admin.Users, filter, {params: params })
            .catch(this.handleError);
    }

   public GetDisciplines(filterQuery: LazyLoadEvent): Observable<Array<Discipline>> {
      let params = new HttpParams();
      let filter = {
         rows: filterQuery ? filterQuery.rows : 0,
         first: filterQuery ? filterQuery.first : 0
      }
      return this.http.post(ApiRouteConstants.Admin.Disciplines, filter, {params: params })
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