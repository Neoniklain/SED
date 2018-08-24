import {Component, Injectable, OnInit} from '@angular/core';
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {NotificationService} from "../../../services/notification.service";
import {StatusType} from "../../../models/statusType.model";
@Component({
    selector: 'settings-page',
    templateUrl: './settings-page.component.html',
    styleUrls: ['./settings-page.component.css']
})
@Injectable()
export class SettingsPageComponent implements OnInit {

    public user: User;
    public showLoader: boolean = true;
    public passwordOld: string = "";
    public passwordNew: string = "";
    public passwordConfirm: string = "";

    private typeOfImg: String= "";
    public image: string = "images/anon-user.jpg";

    constructor(private authenticationService: AuthenticationService,
                private notification: NotificationService) {
    }

    ngOnInit(): void {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                if (this.user.photo !== "")
                    this.image = this.user.photo;
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

    public onFileChange(evt: any) {
        let files = evt.target.files;
        let file = files[0];
        if (files && file) {
            let reader = new FileReader();
            this.typeOfImg = file.type;
            reader.onload = this._handleReaderLoaded.bind(this);
            reader.readAsBinaryString(file);
        }
    }

    private _handleReaderLoaded(readerEvt) {
        let binaryString = readerEvt.target.result;
        this.image = "data:" + this.typeOfImg + ";base64," + btoa(binaryString);
        this.authenticationService.changePhoto(this.image).subscribe(
            result => {
                this.notification.FromStatus(result);
            }
        );
    }

}
