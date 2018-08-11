import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Message} from "primeng/components/common/message";
import {Roles} from "../../../models/account/role.model";

import {AuthenticationService} from "../../../services/authService";
import {HasRoleDirective} from '../../../directive/hasRole.dirictive';
import {Globals} from "../../../globals";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {User} from "../../../models/account/user.model";
import {AccountService} from "../../../services/accountService";
import {StatusType} from "../../../models/statusType.model";

@Component({
   selector: 'header-component',
   templateUrl: "./header.html",
   styleUrls: ["./header.css"]
})

export class HeaderComponent implements OnInit {

   public RouteConstants;
   public Roles;
   public user: User;
   public msgs: Message[] = [];

   constructor(private router: Router,
               private globals: Globals,
               private authService: AuthenticationService,
               /*private accountService: AccountService*/) { }

   ngOnInit() {
      this.RouteConstants = RouteConstants;
      this.Roles = Roles;
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
       this.globals.getUser.subscribe( result => {
           this.user = result;
       });
   }

   logout() {
      localStorage.removeItem("token");
      this.user = new User();
      this.globals.role = [];
      this.router.navigate(['/news']);
   }

}
