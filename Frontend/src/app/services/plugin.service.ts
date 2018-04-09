import {Plugin} from "../models/plugin.model";
import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";


@Injectable()
export class PluginService {
    constructor(private http: HttpClient,
                private router: Router) {
    }

    public GetAll(): Observable<Array<Plugin>> {
        return this.http.get(ApiRouteConstants.Plugin.All)
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