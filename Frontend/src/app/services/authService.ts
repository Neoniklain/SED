import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Router} from "@angular/router";
import {ApiRouteConstants, RouteConstants} from "../bootstrap/app.route.constants";
import {ErrorResponse} from "../models/errorResponse";
import {LogInUser, User} from "../models/user.model";
import {Role} from "../models/role.model";

export const TOKEN_NAME: string = 'token';
 
@Injectable()
export class AuthenticationService {

    public isAuthenticated: boolean = false;

    constructor(private http: HttpClient, private router: Router) { }

    login(user: LogInUser): Observable<any | ErrorResponse> {
        return this.http.post(ApiRouteConstants.Authentication.Login,
            { username: user.username, password: user.password });
    }

    register(user: LogInUser): Observable<any> {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Authentication.Registration, user, {params: params })
            .catch(this.handleError);
    }

    getRole(): Observable<Role[]> {
        return this.http.get(ApiRouteConstants.Authentication.Role)
            .catch(this.handleError);
    }

    getUser(): Observable<User> {
        return this.http.get(ApiRouteConstants.Authentication.User)
            .catch(this.handleError);
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