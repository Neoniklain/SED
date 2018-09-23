export class Role {
    id: number;
    roleName: string;

    constructor() {
        this.id = 0;
        this.roleName = "";
    }
}

export enum Roles {
    Administrator = <any>"ADMIN",
    Engineer = <any>"ENGINEER",
    Professor = <any>"PROFESSOR",
    Student = <any>"STUDENT",
    Anonim = <any>"ANONIM",
    Authorized = <any>"AUTHORIZED"
}