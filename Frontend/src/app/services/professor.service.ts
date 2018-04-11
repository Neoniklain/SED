import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Professor} from "../models/professor.model";
import {Observable} from "rxjs";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {Pair} from "../models/pair.model";


@Injectable()
export class ProfessorService{
    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

    public GetAll(): Observable<Array<Professor>> {
        return this.http.get(ApiRouteConstants.Professor.All)
            .catch(this.handleError);
    }
    public GetChetPairs(id: number): Observable<Array<Pair>> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Professor.GetChetPairs.replace(":id", id.toString()))
            .catch(this.handleError);
    }

    public GetNechetPairs(id: number): Observable<Array<Pair>> {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Professor.GetNechetPairs.replace(":id", id.toString()))
            .catch(this.handleError);
    }

    // public GetPairs(id: number): Observable<Array<Pair>>{
    //     let params: HttpParams = new HttpParams();    //
    //     return this.http.get(ApiRouteConstants.Professor.GetPairs.replace(":id", id.toString()))
    //         .catch(this.handleError);
    // }


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
