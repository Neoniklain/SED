import {Plugin} from "../models/plugin.model";
import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";


@Injectable()
export class PluginService {


    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public GetAll(): Observable<Array<Plugin>> {
        return this.http.get(ApiRouteConstants.Plugin.All)
            .catch(this.handleError.handle);
    }
}