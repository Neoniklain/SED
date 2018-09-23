import {Injectable} from "@angular/core";
import {ResponseStatus} from "../models/additional/responseStatus";
import {StatusType} from "../models/statusType.model";
import {MessageService} from "primeng/api";

@Injectable()
export class NotificationService {

    constructor(private messageService: MessageService) {
    }

    public Success(messsage: string, title?: string) {
        this.messageService.add({severity: 'success', summary: title ? title : "Успешно.", detail: messsage});
    }

    public Error(messsage: string, title?: string) {
        this.messageService.add({severity: 'error', summary: title ? title : "Ошибка.", detail: messsage});
    }

    public Warning(messsage: string, title?: string) {
        this.messageService.add({severity: 'warn', summary: title ? title : "Внимание.", detail: messsage});
    }

    public FromStatus(responseStatus: ResponseStatus) {
        if (responseStatus.status === StatusType.ACCESS_DENIED.toString()) {
            this.Error("Доступ запрещен.");
        }

        if (responseStatus.status === StatusType.NOT_FOUND.toString()) {
            this.Error("Не найдено.");
        }

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