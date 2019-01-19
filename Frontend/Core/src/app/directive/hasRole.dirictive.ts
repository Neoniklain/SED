import {Directive, Input, OnChanges, OnInit, TemplateRef, ViewContainerRef} from "@angular/core";
import {AuthenticationService} from "../services/auth.service";
import {Role, Roles} from "../models/account/role.model";
import {Globals} from "../globals";
import {Subscription} from "rxjs/Subscription";
import {isUndefined} from "util";

@Directive({
    selector: '[hasRole]'
})
export class HasRoleDirective implements OnInit, OnChanges {

    @Input() hasRole: Array<Roles>;
    private roleSubscription: Subscription;
    public userRole: Role[] = [];

    constructor(private viewContainerRef: ViewContainerRef,
                private template: TemplateRef<any>,
                private globals: Globals,
                private authService: AuthenticationService) { }

    ngOnInit() {
        this.roleSubscription = this.globals.getRole.subscribe(result => {
            this.userRole = result;
            this.checkRoles();
        });
    }

    ngOnChanges() {
        this.checkRoles();
    }

    checkRoles() {
        if (!isUndefined(this.hasRole)) {
            // Требуется чтобы был анонимный и нет набора ролей (userRole) - доступ есть
            if (this.hasRole.indexOf(Roles.Anonim) !== -1 && this.userRole.length === 0) {
                this.viewContainerRef.clear();
                this.viewContainerRef.createEmbeddedView(this.template);
                return;
            }
            // Набор ролей (userRole) не пуст и требуется чтобы был авторизован - доступ есть
            if (this.hasRole.indexOf(Roles.Authorized) !== -1 && this.userRole.length > 0) {
                this.viewContainerRef.clear();
                this.viewContainerRef.createEmbeddedView(this.template);
                return;
            }
            // Набор ролей (userRole) пуст - доступа нет
            if (this.userRole.length === 0) {
                this.viewContainerRef.clear();
                return;
            }
            // Требуется определенная роль
            let acces = false;
            for (let r of this.userRole) {
                if (this.hasRole.toString().indexOf(r.roleName) !== -1) acces = true;
            }
            if (acces) {
                this.viewContainerRef.createEmbeddedView(this.template);
            } else {
                this.viewContainerRef.clear();
            }
        }
    }
}