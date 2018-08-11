import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {RouteConstants} from "./app.route.constants";

import {AuthGuard} from "../guards/auth.guard";
import {ListNewsComponent} from "../components/news/list-news/list-news.component";
import {EditorListNewsComponent} from "../components/news/editor-list-news/editor-list-news.component";
import {EditorSingleNewsComponent} from "../components/news/editor-single-news/editor-single-news.component";
import {AccessDeniedComponent} from "../components/shared/accessDenied/accessDenied.component";
import {Roles} from "../models/account/role.model";
import {AdminPanelComponent} from "../components.page/adminPanel/adminPanel";
import {LogInComponent} from "../components.page/login/login.component";
import {AccountComponent} from "../components.page/account/account.component";

export const routes: Routes = [
   {
      path: "",
      component: ListNewsComponent,
      data: {title: "Главная"}
   },
   {
      path: RouteConstants.News.All,
      component: ListNewsComponent,
      data: {title: "Главная"}
   },
   {
      path: RouteConstants.News.Edit,
      component: EditorSingleNewsComponent,
      data: {title: "Редактирование статьи"}
   },
   {
      path: RouteConstants.News.EditList,
      component: EditorListNewsComponent,
      data: {title: "Список статей"}
   },
   {
      path: RouteConstants.News.Create,
      component: EditorSingleNewsComponent,
      canActivate: [AuthGuard],
      data: {expectedRoles: [Roles.Administrator], title: "Создание статьи"}
   },
   {
      path: RouteConstants.Account.Login,
      component: LogInComponent,
      data: {title: "Вход"}
   },
   {
      path: RouteConstants.Account.Lk,
      component: AccountComponent,
      canActivate: [AuthGuard],
      data: {expectedRoles: [Roles.Professor, Roles.Administrator, Roles.Engineer, Roles.Student], title: "Личный кабинет"}
   },
   {
      path: RouteConstants.Account.AccessDenied,
      component: AccessDeniedComponent,
      data: {title: "Доступ запрещен"}
   },
   {
      path: RouteConstants.Admin.All,
      component: AdminPanelComponent,
      canActivate: [AuthGuard],
      data: {expectedRoles: [Roles.Administrator], title: "Админ-панель"}
   }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
