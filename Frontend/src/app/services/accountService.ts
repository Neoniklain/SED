import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ApiRouteConstants, RouteConstants} from "../bootstrap/app.route.constants";

 
@Injectable()
export class AccountService {

    constructor(private http: HttpClient) { }

    public FindUserByName(req: string) {
        return this.http.get(ApiRouteConstants.Account.FindUsersByName.replace(":req", req))
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