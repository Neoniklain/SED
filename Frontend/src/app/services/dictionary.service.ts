import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpErrorResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { ApiRouteConstants } from "../bootstrap/app.route.constants";
import { News } from "../models/news/news.model";
import 'rxjs/add/operator/catch';
import {User} from "../models/account/user.model";
import {Discipline} from "../models/shedule/discipline";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../models/admin/PageResult.model.list";
import {ResponseStatus} from "../models/additional/responseStatus";
import {Group} from "../models/shedule/group";
import {Department} from "../models/shedule/department";
import {Institute} from "../models/shedule/institute";
import {Room} from "../models/shedule/room.model";
import {HandelErrorService} from "./handelError.service";

@Injectable()
export class DictionaryService {

    constructor(
        private http: HttpClient,
        private router: Router,
        private handleError: HandelErrorService
    ) {
    }

    public GetUsers(filterQuery?: LazyLoadEvent): Observable<PageResult> {
       let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Dictonary.Page.Users, this.initFilter(filterQuery), {params: params })
            .catch(this.handleError.handle);
    }
    public GetDisciplines(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Disciplines, this.initFilter(filterQuery), { params: params })
          .catch(this.handleError.handle);
    }
    public GetInstitutes(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Institutes, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError.handle);
    }
    public GetDepartments(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Departments, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError.handle);
    }
    public GetGroups(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Groups, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError.handle);
    }
    public GetRoles(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Roles, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError.handle);
    }
    public GetProfessors(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.Professors, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError.handle);
    }
    public GetFieldOfKnowladge(filterQuery?: LazyLoadEvent): Observable<PageResult> {
      let params = new HttpParams();
      return this.http.post(ApiRouteConstants.Dictonary.Page.FieldOfKnowlage, this.initFilter(filterQuery), {params: params })
          .catch(this.handleError.handle);
    }
    public GetRooms(filterQuery?: LazyLoadEvent): Observable<PageResult> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Dictonary.Page.Rooms, this.initFilter(filterQuery), {params: params })
            .catch(this.handleError.handle);
    }

    public GetPointTypes(filterQuery?: LazyLoadEvent): Observable<PageResult> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Dictonary.Page.PointType, this.initFilter(filterQuery), {params: params })
            .catch(this.handleError.handle);
    }

    public AddOrUpdateDiscipline(discipline: Discipline): Observable<ResponseStatus> {
      let params = new HttpParams();
      return this.http.put(ApiRouteConstants.Dictonary.Page.Disciplines, discipline, {params: params })
          .catch(this.handleError.handle);
    }
    public AddOrUpdateInstitute(institute: Institute): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.put(ApiRouteConstants.Dictonary.Page.Institutes, institute, {params: params })
            .catch(this.handleError.handle);
    }
    public AddOrUpdateDepartment(department: Department): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.put(ApiRouteConstants.Dictonary.Page.Departments, department, {params: params })
            .catch(this.handleError.handle);
    }
    public AddOrUpdateGroups(groups: Group): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.put(ApiRouteConstants.Dictonary.Page.Groups, groups, {params: params })
            .catch(this.handleError.handle);
    }
    public AddOrUpdateRoom(room: Room): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.put(ApiRouteConstants.Dictonary.Page.Rooms, room, {params: params })
            .catch(this.handleError.handle);
    }

    public DeleteDiscipline(id: number): Observable<ResponseStatus> {
      return this.http.delete(ApiRouteConstants.Dictonary.Page.Disciplines + "/" + id)
          .catch(this.handleError.handle);
    }
    public DeleteInstitute(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Dictonary.Page.Institutes + "/" + id)
            .catch(this.handleError.handle);
    }
    public DeleteDepartment(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Dictonary.Page.Departments + "/" + id)
            .catch(this.handleError.handle);
    }
    public DeleteGroup(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Dictonary.Page.Groups + "/" + id)
            .catch(this.handleError.handle);
    }
    public DeleteRole(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Dictonary.Page.Roles + "/" + id)
            .catch(this.handleError.handle);
    }
    public DeleteRoom(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Dictonary.Page.Rooms + "/" + id)
            .catch(this.handleError.handle);
    }
    public DeleteFieldOfKnowladge(id: number): Observable<ResponseStatus> {
        return this.http.delete(ApiRouteConstants.Dictonary.Page.FieldOfKnowlage + "/" + id)
            .catch(this.handleError.handle);
    }

    private initFilter(filterQuery?: LazyLoadEvent) {
      let filter = {
         rows: filterQuery ? filterQuery.rows : 0,
         first: filterQuery ? filterQuery.first : 0,
         globalFilter: filterQuery ? filterQuery.globalFilter : ""
      };
      return filter;
    }
}