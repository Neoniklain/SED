export class ErrorResponse {
    constructor() {
        this.fields = {};
    }
    public key: string;
    public errors: string[];
    public message: string;
    public fields: any;
}