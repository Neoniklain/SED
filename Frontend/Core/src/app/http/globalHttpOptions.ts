import { RequestOptions, RequestMethod, Headers } from "@angular/http";

export class GlobalHttpOptions extends RequestOptions {
    constructor() {
        super({
            method: RequestMethod.Get,
            headers: new Headers({
                "X-Requested-With": "XMLHttpRequest",
            })
        });
    }
}