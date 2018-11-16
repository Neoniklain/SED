import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Router} from "@angular/router";
import {ApiRouteConstants, RouteConstants} from "../bootstrap/app.route.constants";
import {ErrorResponse} from "../models/additional/errorResponse";
import {LogInUser, User} from "../models/account/user.model";
import {ResponseStatus} from "../models/additional/responseStatus";
import {HandelErrorService} from "./handelError.service";
import {catchError, map} from "rxjs/operators";
import {UserAccessRight} from "../models/account/access";
import {Globals} from "../globals";

export const TOKEN_NAME: string = 'token';
 
@Injectable()
export class AuthenticationService {

    constructor(private http: HttpClient,
                private router: Router,
                private globals: Globals,
                private handleError: HandelErrorService) {
    }

    login(user: LogInUser): Observable<any | ErrorResponse> {
        return this.http.post(ApiRouteConstants.Authentication.Login,
            { username: user.username, password: user.password });
    }

    register(user): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Authentication.Registration, user, {params: params })
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    getRole(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Authentication.Role)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    getUser(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Authentication.User)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    getUserAccessRight(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Authentication.UserAccessRight)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    changePass(newPass: string, oldPass: string): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Authentication.ChangePassword
            , {"newPass": newPass, "oldPass": oldPass})
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    changePhoto(photo: string): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Authentication.ChangePhoto
            , photo)
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    logout() {
        localStorage.removeItem(TOKEN_NAME);
        this.globals.role = [];
        this.globals.user = new User();
        this.globals.accessRight = new UserAccessRight();
        this.router.navigate([RouteConstants.Home]);
    }

    removeToken() {
        localStorage.removeItem(TOKEN_NAME);
    }

    getToken(): string {
        return localStorage.getItem(TOKEN_NAME);
    }

    setToken(token: string): void {
        localStorage.setItem(TOKEN_NAME, token);
    }

    redirectToLogin(redirect?: string) {
        const _redirect = redirect ? redirect : this.router.url;
        localStorage.setItem('authRedirect', _redirect);
        this.router.navigate([RouteConstants.News.All]);
    }

   redirectToAccessDenied(redirect?: string) {
      const _redirect = redirect ? redirect : this.router.url;
      localStorage.setItem('authRedirect', _redirect);
      this.router.navigate([RouteConstants.Account.AccessDenied]);
   }

    handleAuth() {
        this.router.navigate([localStorage.getItem('authRedirect') || RouteConstants.News.All]);
        this._clearRedirect();
    }

    private _clearRedirect() {
        localStorage.removeItem('authRedirect');
    }
}