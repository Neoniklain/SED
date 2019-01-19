import { HttpInterceptor, HttpHandler, HttpEvent, HttpRequest } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Rx";
import {AuthenticationService, TOKEN_NAME} from '../services/auth.service';
import { Router } from "@angular/router";
import { RouteConstants, BaseApiUrl } from "../bootstrap/app.route.constants";


@Injectable()
export class ServiceHttpInterceptor implements HttpInterceptor {
    constructor(private router: Router,
      private authService: AuthenticationService,
      ) {};
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem(TOKEN_NAME);

        let headers = req.headers;
        if (token) headers = headers.append("Authorization", token);
        headers = headers.append("X-Requested-With", "XMLHttpRequest");

        if (req.url != '/assets/plugins.config.json') {
            req = req.clone({
                url: BaseApiUrl + req.url,
                headers: headers
            });
        }

        return next.handle(req).catch(err => {
          if (err.status === 403) {
              this.authService.removeToken();
          }
          return Observable.throw(err);
        });
    }
}