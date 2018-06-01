import {Injectable} from "@angular/core";
import {ToastrService} from "ngx-toastr";
import {ResponseStatus} from "../models/additional/responseStatus";

@Injectable()
export class NotificationService {

    constructor(private toastr: ToastrService/*,
                private messageService: MessageService*/) {
    }

    public Success(messsage: string, title?: string) {
        this.toastr.success(messsage, title ? title : "Успешно.");
    }

    public Error(messsage: string, title?: string) {
        this.toastr.error(messsage, title ? title : "Ошибка!");
        console.error(messsage);
    }

    public Warning(messsage: string, title?: string) {
        this.toastr.warning(messsage, title ? title : "Внимание!");
    }

    public FromStatus(responseStatus: ResponseStatus) {

        // this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Новость успешно удалена.'});
        // this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: mes});
        for (let msg of responseStatus.errors) {
            this.Error(msg);
        }

        for (let msg of responseStatus.warnings) {
            this.Warning(msg);
        }

        for (let msg of responseStatus.message) {
            this.Success(msg);
        }
    }
}