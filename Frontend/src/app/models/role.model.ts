export class Role {
  roleName: string;

   constructor(role: string) {
     this.roleName = role;
   }
}

export const Roles = {
   Administrator: "ADMIN",
   Manager: "MANAGER",
   User: "USER",
   Anonim: "ANONIM",
   Authorized: "AUTHORIZED"
}