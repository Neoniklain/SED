"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Role = (function () {
    function Role(role, roleNameRus) {
        this.roleName = role;
        this.roleNameRus = roleNameRus;
    }
    return Role;
}());
exports.Role = Role;
exports.Roles = {
    Administrator: "ADMIN",
    Engineer: "ENGINEER",
    Professor: "PROFESSOR",
    Student: "STUDENT",
    Anonim: "ANONIM",
    Authorized: "AUTHORIZED"
};
