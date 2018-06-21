import {Component, Injectable, OnInit} from '@angular/core';
import {User} from "../../../../models/account/user.model";
import {AuthenticationService} from "../../../../services/authService";
import {NotificationService} from "../../../../services/notification.service";
import {StatusType} from "../../../../models/statusType.model";
@Component({
    selector: 'settings-page',
    templateUrl: './settings-page.component.html',
    styleUrls: ['./settings-page.component.css']
})
@Injectable()
export class SettingsPageComponent implements OnInit {

    public user: User;
    public showLoader: boolean = false;
    public passwordOld: string = "";
    public passwordNew: string = "";
    public passwordConfirm: string = "";

    constructor(private authenticationService: AuthenticationService,
                private notification: NotificationService) {
    }

    ngOnInit(): void {
        this.user = new User();
        this.showLoader = true;
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                this.showLoader = false;
            });
    }

    savePassword() {
        if (this.passwordOld !== this.passwordConfirm)
            this.notification.Error("Пароль подтверждения не совпадает с текущим!");
        this.authenticationService.changePass(this.passwordNew, this.passwordOld).subscribe(
            result => {
                this.notification.FromStatus(result);
            }
        );
    }
}
