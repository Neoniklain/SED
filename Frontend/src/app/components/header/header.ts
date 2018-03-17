import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Message} from "primeng/components/common/message";
import {Roles} from "../../models/account/role.model";

import {AuthenticationService} from "../../services/authService";
import {HasRoleDirective} from '../../guards/hasRole.dirictive';
import {Globals} from "../../globals";
import {RouteConstants} from "../../bootstrap/app.route.constants";
import {User} from "../../models/account/user.model";
import {AccountService} from "../../services/accountService";

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
             this.globals.role = result;
          }
      );
      this.user = new User();
      this.authService.getUser().subscribe(
           res => {
               this.user = res;
           },
           error => {
               if (error.statusText === "Forbidden")
                   this.router.navigate(['/404']);
           });
   }

   // Пытался извне изменить юзера. Не получилось. Отложил на потом.
   /*public setUser(login: string) {
       this.accountService.FindUserByLogin(login).subscribe(
           res => {
               this.user.userFIO = res.userFIO;
               this.user.username = res.username;
               this.user.email = res.email;
               this.user.roles = res.roles;
           });
   }*/

   logout() {
      localStorage.removeItem("token");
      this.user = new User();
      this.globals.role = [];
      this.router.navigate(['/news']);
   }

}
