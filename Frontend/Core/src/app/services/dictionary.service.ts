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
import {Dictionary} from "../models/admin/dictionary.model";
import {StatusType} from "../models/statusType.model";
import {PairType} from "../models/shedule/pairType";
import {Role} from "../models/account/role.model";
import {Professor} from "../models/account/professor";
import {FieldOfKnowledge} from "../models/shedule/fieldOfKnowledge";
import {PointType} from "../models/journal/journal.model";
import {catchError, map} from "rxjs/operators";
import {Speciality} from "../models/shedule/speciality";

@Injectable()
export class DictionaryService {

    constructor(
        private http: HttpClient,
        private router: Router,
        private handleError: HandelErrorService
    ) {
    }

    public Get(type: Dictionary, filterQuery?: LazyLoadEvent): Observable<PageResult> {
        let params = new HttpParams();
        let url = this.GetUrl(type);
        return this.http.post(url, this.initFilter(filterQuery), {params: params })
            .pipe(
                map((res: PageResult) => {
                    let model = this.CreateInstance(type);
                    model = res.content;
                    res.content = model;
                    return res;
                }),
                catchError(e => this.handleError.handle(e))
            );
    }

    public AddOrUpdate(type: Dictionary, object: any): Observable<ResponseStatus> {
        let params = new HttpParams();
        let url = this.GetUrl(type);
        return this.http.put(url, object, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public Delete(type: Dictionary, id: number): Observable<ResponseStatus> {
        let url = this.GetUrl(type);
        return this.http.delete(url + "/" + id)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    private CreateInstance(type: Dictionary): any {
        let model;
        switch (type.toString()) {
            case Dictionary.users.toString():
                model = new Array<User>();
                break;
            case Dictionary.disciplines.toString():
                model = new Array<Discipline>();
                break;
            case Dictionary.groups.toString():
                model = new Array<Group>();
                break;
            case Dictionary.institutes.toString():
                model = new Array<Institute>();
                break;
            case Dictionary.departments.toString():
                model = new Array<Department>();
                break;
            case Dictionary.specialities.toString():
                model = new Array<Speciality>();
                break;
            case Dictionary.rooms.toString():
                model = new Array<Room>();
                break;
            case Dictionary.pairTypes.toString():
                model = new Array<PairType>();
                break;
            case Dictionary.roles.toString():
                model = new Array<Role>();
                break;
            case Dictionary.professors.toString():
                model = new Array<Professor>();
                break;
            case Dictionary.fieldOfKnowladge.toString():
                model = new Array<FieldOfKnowledge>();
                break;
            case Dictionary.pointTypes.toString():
                model = new Array<PointType>();
                break;
            default:
                model = new Array();
                break;
        }
        return model;
    }

    private GetUrl(type: Dictionary): string {
        let url: string = "";
        switch (type.toString()) {
            case Dictionary.users.toString():
                url = ApiRouteConstants.Dictonary.Page.Users;
                break;
            case Dictionary.disciplines.toString():
                url = ApiRouteConstants.Dictonary.Page.Disciplines;
                break;
            case Dictionary.groups.toString():
                url = ApiRouteConstants.Dictonary.Page.Groups;
                break;
            case Dictionary.institutes.toString():
                url = ApiRouteConstants.Dictonary.Page.Institutes;
                break;
            case Dictionary.departments.toString():
                url = ApiRouteConstants.Dictonary.Page.Departments;
                break;
            case Dictionary.specialities.toString():
                url = ApiRouteConstants.Dictonary.Page.Specialities;
                break;
            case Dictionary.rooms.toString():
                url = ApiRouteConstants.Dictonary.Page.Rooms;
                break;
            case Dictionary.pairTypes.toString():
                url = ApiRouteConstants.Dictonary.Page.PairTypes;
                break;
            case Dictionary.roles.toString():
                url = ApiRouteConstants.Dictonary.Page.Roles;
                break;
            case Dictionary.professors.toString():
                url = ApiRouteConstants.Dictonary.Page.Professors;
                break;
            case Dictionary.fieldOfKnowladge.toString():
                url = ApiRouteConstants.Dictonary.Page.FieldOfKnowlage;
                break;
            case Dictionary.pointTypes.toString():
                url = ApiRouteConstants.Dictonary.Page.PointType;
                break;
            default:
                url = "";
                break;
        }
        return url;
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