import { HttpInterceptor, HttpHandler, HttpEvent, HttpRequest, HttpHeaders } from '@angular/common/http';

import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Rx";
import { TOKEN_NAME } from '../services/authService';
import { Router } from "@angular/router";
import { RouteConstants, BaseApiUrl, ApiRouteConstants } from "../bootstrap/app.route.constants";


@Injectable()
export class ServiceHttpInterceptor implements HttpInterceptor {
    constructor(private router: Router,
      private toastr: ToastrService,
      ) {};
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem(TOKEN_NAME);

        let headers = req.headers;
        if (token) headers = headers.append("Authorization", token);
        headers = headers.append("X-Requested-With", "XMLHttpRequest");

        req = req.clone({
            url: BaseApiUrl + req.url,
            headers: headers
        });

        return next.handle(req).catch(err => {
          if (err.status === 401 || err.status === 403) {
              this.router.navigate([RouteConstants.Account.Login]);
          }
          else if (err.status !== 400) {
            console.log(err);
            this.toastr.error("Не удалось выполнить запрос. Повторите попытку позже.", "Ошибка выполнения");
          }
          return Observable.throw(err);
        });
    }
}