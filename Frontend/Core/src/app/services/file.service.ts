import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {ApiRouteConstants, BaseApiUrl} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";
import {ResponseStatus} from "../models/additional/responseStatus";
import {catchError, map} from "rxjs/operators";
import {ArrayType} from "@angular/compiler";

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
    /*
    public downloadFile(fileId: number): void {
        let a: HTMLAnchorElement = document.createElement('a');
        let dataURI: string = BaseApiUrl + ApiRouteConstants.File.Download + fileId.toString();
        a.href = dataURI;
        let e: MouseEvent = document.createEvent('MouseEvents');
        e.initMouseEvent('click', true, false,
            document.defaultView, 0, 0, 0, 0, 0,
            false, false, false, false, 0, null);
        a.dispatchEvent(e);
        a.remove();
    }
    */

    private b64toBlob(b64Data, contentType, sliceSize) {
        contentType = contentType || '';
        sliceSize = sliceSize || 512;

        let byteCharacters = atob(b64Data);
        let byteArrays = [];

        for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            let slice = byteCharacters.slice(offset, offset + sliceSize);

            let byteNumbers = new Array(slice.length);
            for (let i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }

            let byteArray = new Uint8Array(byteNumbers);

            byteArrays.push(byteArray);
        }

        let blob = new Blob(byteArrays, {type: contentType});
        return blob;
    }

    public downloadFile(fileId: number) {
        let result = this.http.get(ApiRouteConstants.File.Download + fileId.toString())
            .pipe(
                map((res: ResponseStatus) => res),
                catchError(e => this.handleError.handle(e))
            );
        result.subscribe(
            res => {
                let a = document.createElement("a");
                let blob = this.b64toBlob(res.data.data, res.data.fileDescription.fileType, 512);
                a.href = window.URL.createObjectURL(blob);
                a.download = res.data.fileDescription.fileName;
                a.click();
                a.remove();
            },
            error => {
                console.error("download", error);
            }
        );
    }
}
