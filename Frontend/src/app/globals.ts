import {Role} from "./models/account/role.model";
import {Injectable, Input} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {User} from "./models/account/user.model";

Injectable();
export class Globals {

   private _role: BehaviorSubject<Role[]> = new BehaviorSubject([]);

   private _user: BehaviorSubject<User> = new BehaviorSubject(new User);

   public getRole = this._role.asObservable();
   public getUser = this._user.asObservable();

   set role(value: Role[]) {
      this._role.next(value);
   }

    set user(value: User) {
        this._user.next(value);
    }

}