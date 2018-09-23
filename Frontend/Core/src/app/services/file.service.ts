import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {ApiRouteConstants, BaseApiUrl} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";

@Injectable()
export class  FileService {

    constructor(private http: HttpClient,
                private handleError: HandelErrorService) {
    }

    public getFilesForObject(objectTypeId: number, objectId: number): Observable<ResponseStatus> {
        return this.http.get(ApiRouteConstants.File.GetFileForObject
            .replace(":objectTypeId", objectTypeId.toString())
            .replace(":objectId", objectId.toString()))
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    // грузим файл из базы
    public downloadFile(fileId: number): void {
        let a: HTMLAnchorElement = document.createElement('a');
        let dataURI: string = BaseApiUrl + ApiRouteConstants.File.Download + fileId.toString();
        a.href = dataURI;
        let e: MouseEvent = document.createEvent('MouseEvents');
        // use of deprecated function to satisfy TypeScript.
        e.initMouseEvent('click', true, false,
            document.defaultView, 0, 0, 0, 0, 0,
            false, false, false, false, 0, null);
        a.dispatchEvent(e);
        a.remove();
    }
}
