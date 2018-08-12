import {Directive, Input, OnChanges, OnInit, TemplateRef, ViewContainerRef} from "@angular/core";
import {Role, Roles} from "../models/account/role.model";
import {Globals} from "../globals";
import {Subscription} from "rxjs/Subscription";
import {isUndefined} from "util";
import {AccessRightType, UserAccessRight} from "../models/account/access";

@Directive({
   selector: '[hasAccessRight]'
})
export class HasAccessRightDirective implements OnInit, OnChanges {

   @Input() hasAccessRight: Array<AccessRightType>;
   private roleSubscription: Subscription;
   public accessRight: UserAccessRight = new UserAccessRight();

   constructor(private viewContainerRef: ViewContainerRef,
               private template: TemplateRef<any>,
               private globals: Globals) { }

   ngOnInit() {
      this.roleSubscription = this.globals.getAccessRight.subscribe(result => {
         this.accessRight = result;
         this.checkAccess();
      });
   }

   ngOnChanges() {
      this.checkAccess();
   }

    checkAccess() {
        if (!isUndefined(this.hasAccessRight)) {
            let acces = false;
            for (let row of this.accessRight.rights) {
                if (row.right.name === this.hasAccessRight.toString()) {
                    acces = row.allow;
                }
            }
            if (acces) {
                this.viewContainerRef.createEmbeddedView(this.template);
            } else {
                this.viewContainerRef.clear();
            }
      }
   }
}