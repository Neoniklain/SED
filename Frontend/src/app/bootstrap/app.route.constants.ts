import { appSettings } from './app.environment';

export const BaseApiUrl = appSettings.BaseApiUrl;

export const RouteConstants = {
    News: {
        All: "news",
        Create: "news/create",
        Edit: "news/:id/edit",
        EditList: "news/editList"
    },
    Account: {
        All: "account",
        Login: "account/login",
        Register: "account/register"
    },
    NotFound: "404"
}

export const ApiRouteConstants = {
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
    }
}