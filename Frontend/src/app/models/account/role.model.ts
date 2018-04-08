export class Role {
    id: number;
    roleName: string;

    constructor() {
        this.id = 0;
        this.roleName = "";
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