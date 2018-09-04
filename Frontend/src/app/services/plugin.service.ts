import {Plugin} from "../models/plugin.model";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";
import {catchError, map} from "rxjs/operators";


@Injectable()
export class PluginService {


    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public GetAll(): Observable<Array<Plugin>> {
        return this.http.get(ApiRouteConstants.Plugin.All)
            .pipe(
                map((res: Array<Plugin>) => res),
                catchError(e => this.handleError.handle(e))
            );
    }
}