import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Message} from "primeng/components/common/message";
import {Roles} from "../../models/user.model";
import {Globals, Role} from "../../models/role.model";
import {AuthenticationService} from "../../services/authService";

@Component({
   selector: 'header-component',
   templateUrl: "./header.html",
   styleUrls: ["./header.css"],
   providers: [ Globals ]
})

export class HeaderComponent implements OnInit {

   accountService: any;
   public Roles;
   public msgs: Message[] = [];

   constructor(private router: Router,
               private globals: Globals,
               private authService: AuthenticationService) {

   }

   ngOnInit(): void {
      this.Roles = Roles;
      this.authService.getRole().subscribe(
          result => {
             this.globals.setRole = result;
          }
      );
   }

   logout(): void {
      console.log("logout");
      localStorage.removeItem("token");
      this.globals.setRole([new Role(Roles.Anonim)]);
      this.router.navigate(['/news']);
   }

}
