import {Role} from "./models/account/role.model";
import {Injectable, Input} from "@angular/core";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {User} from "./models/account/user.model";
import {UserAccessRight} from "./models/account/access";

Injectable();
export class Globals {

    private _role: BehaviorSubject<Role[]> = new BehaviorSubject([]);
    private _user: BehaviorSubject<User> = new BehaviorSubject(new User);
    private _accessRight: BehaviorSubject<UserAccessRight> = new BehaviorSubject(new UserAccessRight);
    private _pluginCore: BehaviorSubject<any> = new BehaviorSubject([]);

    public getRole = this._role.asObservable();
    public getUser = this._user.asObservable();
    public getAccessRight = this._accessRight.asObservable();
    public getPluginCore = this._pluginCore.asObservable();

    set role(value: Role[]) {
      this._role.next(value);
   }

    set user(value: User) {
        this._user.next(value);
    }

    set accessRight(value: UserAccessRight) {
        this._accessRight.next(value);
    }

    set pluginCore(value: any) {
        this._pluginCore.next(value);
    }

}