import {appSettings} from './app.environment';

export const BaseApiUrl = appSettings.BaseApiUrl;

export const RouteConstants = {
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
   Professor: {
      All: "professors",
      GetPairs:"professor/:id/pairs"
   },
   Group:{
      All: "groups",
      GetPairs: "group/:id/pairs"
   },
   Department:{
      All:"departments",
      GetPairs:"department/pairs"
   },
   Pair:{
      All:"pairs",
      Create:"pair/create",
      Edit:"pair/:id/edit",
      EditList:"professor/:id/pairs/editList"
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
   },
   Excel:
       {
          ParseStudyPlan: "excel/ParseStudyPlan"
       },
   Admin: {
      Page: {
         Disciplines: "admin/page/disciplines",
         Users: "admin/page/users"
      }
   },
   Professor: {
      All: "professors",
      GetChetPairs:"professor/:id/pairs/even",
      GetNechetPairs:"professor/:id/pairs/odd"
   },
   Group: {
      All: "groups",
      GetChetPairs:"group/:id/pairs/even",
      GetNechetPairs:"group/:id/pairs/odd"
   },
   Department:{
      GetChetPairs:"department/even",
      GetNechetPairs:"department/odd"
   },

   Pair:{
      All:"pairs",
      Save:"pairs/save",
      Get: "pair/get/:id",
      Delete: "pair/delete/:id"
   },
   Discipline:{
      All:"disciplines"
   },
   Room:{
      All:"rooms"
   },
   Plugin:{
      All:"plugins"
   }
}