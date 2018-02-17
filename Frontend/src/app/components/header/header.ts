import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Message} from "primeng/components/common/message";
import {Roles} from "../../models/role.model";

import {AuthenticationService} from "../../services/authService";
import {HasRoleDirective} from '../../guards/hasRole.dirictive';
import {Globals} from "../../globals";
import {RouteConstants} from "../../bootstrap/app.route.constants";

@Component({
   selector: 'header-component',
   templateUrl: "./header.html",
   styleUrls: ["./header.css"]
})

export class HeaderComponent implements OnInit {

   public RouteConstants;
   accountService: any;
   public Roles;
   public msgs: Message[] = [];

   constructor(private router: Router,
               private globals: Globals,
               private authService: AuthenticationService) { }

   ngOnInit() {
      this.RouteConstants = RouteConstants;
      this.Roles = Roles;
      this.authService.getRole().subscribe(
          result => {
             this.globals.role = result;
          }
      );
   }

   logout() {
      localStorage.removeItem("token");
      this.globals.role = [];
      this.router.navigate(['/news']);
   }

}
