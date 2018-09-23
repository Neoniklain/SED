import {HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {NotificationService} from "./notification.service";
import {Injectable} from "@angular/core";

@Injectable()
export class HandelErrorService {

    constructor(private notification: NotificationService) {
    }

    public handle(error: HttpErrorResponse | any): Observable<any> {
        let errMsg: string;
        if (error instanceof HttpErrorResponse) {
            const body = error.error || "";
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ""} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        if (error.status == 403 || error.status == 401) {
            this.notification.Error("Аторизация не удалась.");
        } else {
            this.notification.Error("Не удалось выполнить запрос. Повторите попытку позже.", "Ошибка выполнения");
        }
        return Observable.throwError(errMsg);
    }

}

