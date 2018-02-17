import {EventEmitter, Injectable, Input, Output} from '@angular/core';
import {Roles} from "./user.model";

@Injectable()
export class Globals {
   private role: Role[] = [new Role(Roles.Anonim)];
   @Input()
   stringChanged = new EventEmitter();
   @Input()
   set setRole(value){
      console.log("role changed from", this.role, "to", value);
      this.stringChanged.emit();
      this.role = value;
   }
   get getRole(){
      console.log("return role changed from", this.role);
      return this.role;
   }
}

export class Role {
  roleName: string;

   constructor(role: string) {
     this.roleName = role;
   }
}


