import {Component, OnChanges, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {User} from "../../../models/account/user.model";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {AccountService} from "../../../services/accountService";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";
import {UserAccessRight} from "../../../models/account/access";

@Component({
   selector: 'access-control',
   templateUrl: "./accessControl.component.html",
   styleUrls: ["./accessControl.component.css"],
})

export class AccessControlComponent implements OnInit, OnChanges {

    public model: User = null;
    public acceses: UserAccessRight = new UserAccessRight();
    public Dictionary = Dictionary;

    constructor(private router: Router,
                private accountService: AccountService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {}

    ngOnChanges(): void {}

    selectUser(user: User) {
        this.model = user;
        this.getUserAccess();
    }

    getUserAccess() {
        if (!this.model || this.model.id === 0) {
            console.error("Не указан пользователь для запроса его прав.")
            return;
        }

        this.accountService.GetUserAccessRight(this.model.id).subscribe(
            reult => {
                if (reult.status === StatusType.OK.toString()) {
                    this.acceses = reult.data;
                    this.acceses.rights.sort(function(a, b){
                        if (a.right.header < b.right.header) return -1;
                        if (a.right.header > b.right.header) return 1;
                        return 0;
                    });
                }
                this.notificationService.FromStatus(reult);
            }
        );
    }
    back() {
        this.model = null;
    }

    save() {
        this.accountService.SaveUserAccessRight(this.acceses).subscribe(
            reult => {
                this.notificationService.FromStatus(reult);
            }
        );
    }

}
