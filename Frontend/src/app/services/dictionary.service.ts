import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news/news.model";
import 'rxjs/add/operator/catch';
import {User} from "../models/account/user.model";
import {Discipline} from "../models/discipline.model";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../models/admin/PageResult.model.list";
import {ResponseStatus} from "../models/additional/responseStatus";

@Injectable()
export class DictionaryService {

    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

   public GetUsers(filterQuery?: LazyLoadEvent): Observable<PageResult> {
       let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Dictonary.Page.Users, this.initFilter(filterQuery), {params: params })
            .catch(this.handleError);
    }
   public GetDisciplines(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Disciplines, this.initFilter(filterQuery), { params: params })
          .catch(this.handleError);
   }
   public GetInstitutes(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Institutes, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError);
   }
   public GetDepartments(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Departments, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError);
   }
   public GetGroups(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Groups, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError);
   }
   public GetRoles(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Roles, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError);
   }
   public GetFieldOfKnowlage(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.FieldOfKnowlage, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError);
   }

   public AddOrUpdateDiscipline(discipline: Discipline): Observable<ResponseStatus> {
      let params = new HttpParams();
      return this.http.put(ApiRouteConstants.Dictonary.Page.Disciplines, discipline, {params: params })
          .catch(this.handleError);
   }

   public DeleteDiscipline(id: number): Observable<ResponseStatus> {
      return this.http.delete(ApiRouteConstants.Dictonary.Page.Disciplines + "/" + id)
          .catch(this.handleError);
   }

   private initFilter(filterQuery?: LazyLoadEvent) {
      let filter = {
         rows: filterQuery ? filterQuery.rows : 0,
         first: filterQuery ? filterQuery.first : 0,
         globalFilter: filterQuery ? filterQuery.globalFilter : ""
      };
      return filter;
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