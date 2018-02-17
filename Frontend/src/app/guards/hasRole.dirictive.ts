import {Directive, Input, OnInit, TemplateRef, ViewContainerRef} from "@angular/core";
import {AuthenticationService} from "../services/authService";
import {Globals, Role} from "../models/role.model";
import {Roles} from "../models/user.model";

@Directive({
   selector: '[hasRole]'
})
export class HasRoleDirective implements OnInit {
   @Input() hasRole: Array<string>;

   constructor(private viewContainerRef: ViewContainerRef,
               private template: TemplateRef<any>,
               private globals: Globals,
               private authService: AuthenticationService) {
   }

   ngOnInit() {
      this.globals.stringChanged = this.checkRoles();
   }

   checkRoles() {
      if (this.globals.getRole.indexOf(new Role(Roles.Anonim)) !== -1 && this.hasRole.indexOf(Roles.Anonim) !== -1) {
         console.log("Есть доступ");
         this.viewContainerRef.createEmbeddedView(this.template);
      }
      else {
         let acces = false;
         for (let r of this.globals.getRole) {
            console.log("Роль пользователя: " + r.roleName);
            if (this.hasRole.indexOf(r.roleName) !== -1) acces =  true;
         }
         if (!this.hasRole || acces) {
            console.log("Есть доступ");
            this.viewContainerRef.createEmbeddedView(this.template);
         } else {
            this.viewContainerRef.clear();
            console.log("Доступ запрещен");
         }
      }
   }
}