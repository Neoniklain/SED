import {Router} from "@angular/router";
import {HttpClient, HttpParams, HttpErrorResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Pair} from "../models/pair.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {Observable} from "rxjs";



@Injectable()
export class PairService {

    constructor(private http: HttpClient,
                private router: Router) {
    }

    public Get(id: number) {
        let params: HttpParams = new HttpParams();
        return this.http.get(ApiRouteConstants.Pair.Get.replace(":id", id.toString()))
            .catch(this.handleError);
    }


    public Save(pair: Pair) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Pair.Save, pair, {params: params })
            .catch(this.handleError);
    }

    public Delete(id: number) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Pair.Delete.replace(":id", id.toString()), null, {params: params}).catch(this.handleError);
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