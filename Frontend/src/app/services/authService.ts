import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Router} from "@angular/router";
import {ApiRouteConstants, RouteConstants} from "../bootstrap/app.route.constants";
import {ErrorResponse} from "../models/additional/errorResponse";
import {LogInUser, User} from "../models/account/user.model";
import {Role} from "../models/account/role.model";
import {ResponseStatus} from "../models/additional/responseStatus";
import {HandelErrorService} from "./handelError.service";
import {RequestOptions} from "@angular/http";

export const TOKEN_NAME: string = 'token';
 
@Injectable()
export class AuthenticationService {

    public isAuthenticated: boolean = false;

    constructor(private http: HttpClient,
                private router: Router,
                private handleError: HandelErrorService) {
    }

    login(user: LogInUser): Observable<any | ErrorResponse> {
        return this.http.post(ApiRouteConstants.Authentication.Login,
            { username: user.username, password: user.password });
    }

    register(user): Observable<ResponseStatus> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Authentication.Registration, user, {params: params })
            .catch(this.handleError.handle);
    }

    getRole(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Authentication.Role)
            .catch(this.handleError.handle);
    }

    getUser(): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.Authentication.User)
            .catch(this.handleError.handle);
    }

    changePass(newPass: string, oldPass: string): Observable<ResponseStatus> {
        return this.http.post(ApiRouteConstants.Authentication.ChangePassword
            , {"newPass": newPass, "oldPass": oldPass});
    }

    logout() {
        localStorage.removeItem(TOKEN_NAME);
        this.isAuthenticated = false;
        this.router.navigate([ApiRouteConstants.Authentication.Login]);
    }

    getToken(): string {
        return localStorage.getItem(TOKEN_NAME);
    }

    setToken(token: string): void {
        localStorage.setItem(TOKEN_NAME, token);
        this.isAuthenticated = true;
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