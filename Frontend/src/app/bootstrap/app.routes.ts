import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {RouteConstants} from "./app.route.constants";

import {AuthGuard} from "../guards/auth.guard";
import {ListNewsComponent} from "../components/news/list-news/list-news.component";
import {AccountComponent} from "../components/account/account/account.component";
import {LogInComponent} from "../components/account/authentification/login/login.component";
import {RegisterComponent} from "../components/account/authentification/register/register.component";
import {EditorListNewsComponent} from "../components/news/editor-list-news/editor-list-news.component";
import {EditorSingleNewsComponent} from "../components/news/editor-single-news/editor-single-news.component";
import {AccessDeniedComponent} from "../components/account/accessDenied/accessDenied.component";
import {AdminPanelComponent} from "../components/adminPanel/adminPanel";
import {Roles} from "../models/role.model";

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
      path: RouteConstants.Account.Register,
      component: RegisterComponent,
      data: {title: "Регистрация"}
   },
   {
      path: RouteConstants.Account.Lk,
      component: AccountComponent,
      canActivate: [AuthGuard],
      data: {title: "Личный кабинет"}
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
   },

   {
      path: RouteConstants.Professor.All,
      component: ListProfessorsComponent,
      data: { title: "Расписание" }
   },

   {
      path: RouteConstants.Professor.GetPairs,
      component: ListPairsProfessorComponent,
      data: { title: "Расписание преподавателя" }
   },

   {
      path: RouteConstants.Group.GetPairs,
      component: ListPairsGroupComponent,
      data: { title: "Расписание группы" }
   },

   {
      path: RouteConstants.Department.GetPairs,
      component: ListPairsDepartmentComponent,
      data: { title: "Расписание кафедры" }
   },

   {
      path: RouteConstants.Pair.Create,
      component: EditorSinglePairComponent,
      data: { title: "Создание пары" }
   },

   {
      path: RouteConstants.Pair.Edit,
      component: EditorSinglePairComponent,
      data: { title: "Редактирование пары" }
   },

   {
      path: RouteConstants.Pair.EditList,
      component: EditorListPairsComponent,
      data: { title: "Список пар" }
   }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
