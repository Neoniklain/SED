import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { RouteConstants } from "./app.route.constants";

import { AuthGuard } from "../guards/auth.guard";
import {ListNewsComponent} from "../components/news/list-news/list-news.component";
import {AccountComponent} from "../components/account/account/account.component";
import {LogInComponent} from "../components/account/authentification/login/login.component";
import {RegisterComponent} from "../components/account/authentification/register/register.component";
import {EditorListNewsComponent} from "../components/news/editor-list-news/editor-list-news.component";
import {EditorSingleNewsComponent} from "../components/news/editor-single-news/editor-single-news.component";

export const routes: Routes = [
    {
        path: "",
        component: ListNewsComponent,
        data: { title: "Главная" }
    },
    {
        path: RouteConstants.News.All,
        component: ListNewsComponent,
        data: { title: "Главная" }
    },
    {
        path: RouteConstants.News.Edit,
        component: EditorSingleNewsComponent,
        data: { title: "Редактирование статьи" }
    },
    {
        path: RouteConstants.News.EditList,
        component: EditorListNewsComponent,
        data: { title: "Список статей" }
    },
    {
        path: RouteConstants.News.Create,
        component: EditorSingleNewsComponent,
        data: { title: "Создание статьи" }
    },
    {
        path: RouteConstants.Account.Login,
        component: LogInComponent,
        data: { title: "Вход" }
    },
    {
        path: RouteConstants.Account.Register,
        component: RegisterComponent,
        data: { title: "Регистрация" }
    },
    {
        path: RouteConstants.Account.All,
        component: AccountComponent,
        canActivate: [ AuthGuard ],
        data: { title: "Личный кабинет" }
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
