import {Role} from "./models/account/role.model";
import {Injectable, Input} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

Injectable();
export class Globals {
   private _role: BehaviorSubject<Role[]> = new BehaviorSubject([]);
   public getRole = this._role.asObservable();

   set role(value) {
      this._role.next(value);
   }

}