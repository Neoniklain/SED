import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Message} from "primeng/components/common/message";
import {Roles} from "../../../models/account/role.model";

import {AuthenticationService} from "../../../services/authService";
import {Globals} from "../../../globals";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {User} from "../../../models/account/user.model";
import {StatusType} from "../../../models/statusType.model";
import {AccountService} from "../../../services/accountService";
import {AccessRightType, UserAccessRight} from "../../../models/account/access";

@Component({
   selector: 'header-component',
   templateUrl: "./header.html",
   styleUrls: ["./header.css"]
})

export class HeaderComponent implements OnInit {

    public RouteConstants = RouteConstants;
    public AccessRightType = AccessRightType;
    public Roles = Roles;
    public user: User;
    public userAccessRight: UserAccessRight;
    public msgs: Message[] = [];

    constructor(private router: Router,
               private globals: Globals,
               private authService: AuthenticationService) { }

    ngOnInit() {
      this.authService.getRole().subscribe(
          result => {
             this.globals.role = result.data;
          }
      );

      this.user = new User();

      this.authService.getUser().subscribe(
           res => {
               if (res.status === StatusType.OK.toString()) {
                   this.user = res.data;
                   if (this.user.photo === "")
                       this.user.photo = "images/anon-user.jpg";
                   this.globals.user = this.user;
               } else {
                   this.user = null;
                   this.globals.user = null;
               }
           });

        this.authService.getUserAccessRight().subscribe(
            res => {
                if (res.status === StatusType.OK.toString()) {
                  this.userAccessRight = res.data;
                  this.globals.accessRight = this.userAccessRight;
              }
          });

        this.globals.getUser.subscribe( result => {
        this.user = result;
        });
    }

    logout() {
      localStorage.removeItem("token");
      this.user = new User();
      this.globals.role = [];
      this.globals.user = new User();
      this.globals.accessRight = new UserAccessRight();
      this.router.navigate(['/news']);
    }

}
