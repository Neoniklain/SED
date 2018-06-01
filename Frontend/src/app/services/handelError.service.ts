import {HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {NotificationService} from "./notification.service";
import {Injectable} from "@angular/core";

@Injectable()
export class HandelErrorService {

    constructor(private notification: NotificationService) {
    }

    public handle(error: HttpErrorResponse | any) {
        let errMsg: string;
        if (error instanceof HttpErrorResponse) {
            const body = error.error || "";
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ""} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        this.notification.Error(errMsg);
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

}

