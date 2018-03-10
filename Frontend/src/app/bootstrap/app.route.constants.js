"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var app_environment_1 = require("./app.environment");
exports.BaseApiUrl = app_environment_1.appSettings.BaseApiUrl;
exports.RouteConstants = {
    Home: "news",
    News: {
        All: "news",
        Create: "news/create",
        Edit: "news/:id/edit",
        EditList: "news/editList"
    },
    Account: {
        Lk: "account",
        Login: "account/login",
        Register: "account/register",
        AccessDenied: "account/accessDenied"
    },
    Admin: {
        All: "admin"
    },
    Journal: {
        All: "journal"
    },
    NotFound: "404"
};
exports.ApiRouteConstants = {
    Account: {
        FindUsersByName: "account/FindUsersByName/:req"
    },
    Authentication: {
        Login: "account/login",
        Registration: "account/registration",
        Role: "account/role",
        User: "account/user"
    },
    News: {
        All: "news/all",
        Last: "news/last",
        Get: "news/get/:id",
        Delete: "news/delete/:id",
        Save: "news/save"
    },
    Issue: {
        All: "issue/list",
        Create: "issue/create",
        Update: "issue/update",
        Get: "issue/get/:id",
        Delete: "issue/delete/:id"
    },
    Journal: {
        All: "jurnal/all"
    },
    Excel: {
        ParseStudyPlan: "excel/ParseStudyPlan"
    },
    Dictonary: {
        Page: {
            Disciplines: "admin/page/disciplines",
            Users: "admin/page/users",
            Institutes: "admin/page/institutes",
            Departments: "admin/page/departments",
            Groups: "admin/page/grousp",
            Roles: "admin/page/roles",
            FieldOfKnowlage: "admin/page/fieldOfKnowledge",
        }
    }
};
