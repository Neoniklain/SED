import {Compiler, Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Roles} from "../../../models/account/role.model";

import {AuthenticationService} from "../../../services/auth.service";
import {Globals} from "../../../globals";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {User} from "../../../models/account/user.model";
import {StatusType} from "../../../models/statusType.model";
import {AccessRightType, UserAccessRight} from "../../../models/account/access";
import {HttpClient} from "@angular/common/http";
import {Message} from "primeng/components/common/message";

@Component({
   selector: 'header-component',
   templateUrl: "./header.html",
   styleUrls: ["./header.css"]
})

export class HeaderComponent implements OnInit {

    public RouteConstants = RouteConstants;
    public AccessRightType = AccessRightType;
    public Roles = Roles;
    public user: User = new User();
    public userAccessRight: UserAccessRight = new UserAccessRight();
    public msgs: Message[] = [];

    constructor(private compiler: Compiler,
                private router: Router,
                private http: HttpClient,
                private globals: Globals,
                private authService: AuthenticationService) { }

    ngOnInit() {
        if (this.authService.getToken() && this.authService.getToken() != "") {
            this.authService.getRole().subscribe(
                result => {
                    this.globals.role = result.data;

                    this.authService.getUser().subscribe(
                    res => {
                       if (res.status === StatusType.OK.toString()) {
                           this.user = res.data;
                           if (this.user.photo === "") this.user.photo = "images/anon-user.jpg";
                           this.globals.user = this.user;
                       } else {
                           this.user = new User();
                           this.userAccessRight = new UserAccessRight();
                       }
                    });

                    this.authService.getUserAccessRight().subscribe(
                    res => {
                            if (res.status === StatusType.OK.toString()) {
                            this.userAccessRight = res.data;
                            this.globals.accessRight = this.userAccessRight;
                        }
                    });

                }
            );
        }
        this.globals.getUser.subscribe( result => this.user = result );
        this.globals.getAccessRight.subscribe( result => this.userAccessRight = result );
    }

    logout() {
        this.authService.logout();
    }

}
