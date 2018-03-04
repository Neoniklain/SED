export class Role {
  roleName: string;
  roleNameRus: string;

   constructor(role: string, roleNameRus: string) {
     this.roleName = role;
     this.roleNameRus = roleNameRus;
   }
}

export const Roles = {
    Administrator: "ADMIN",
    Engineer: "ENGINEER",
    Professor: "PROFESSOR",
    Student: "STUDENT",
    Anonim: "ANONIM",
    Authorized: "AUTHORIZED"
}