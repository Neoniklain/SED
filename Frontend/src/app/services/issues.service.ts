import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {Issue} from "../models/workflow/issue.model";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";

@Injectable()
export class IssueService {
    constructor(private http: HttpClient) { }

    public Get(id: number) {
      let params: HttpParams = new HttpParams();
      return this.http.get(ApiRouteConstants.Issue.Get.replace(":id", id.toString()))
          .catch(this.handleError);
    }

    public Delete(id: number) {
      return this.http.get(ApiRouteConstants.Issue.Delete.replace(":id", id.toString()))
          .catch(this.handleError);
    }

    public GetList(): Observable<Array<Issue>> {
    return this.http.get(ApiRouteConstants.Issue.All)
        .catch(this.handleError);
    }

    public Update(issue: Issue) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Issue.Update, issue, {params: params })
        .catch(this.handleError);
    }

    public Create(issueName: Issue) {
        let params = new HttpParams();
        return this.http.post(ApiRouteConstants.Issue.Create, issueName, {params: params })
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
