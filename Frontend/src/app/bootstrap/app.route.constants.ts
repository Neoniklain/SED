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
      GetPairs: "professor/:id/pairs"
   },
   Group: {
      All: "groups",
      GetPairs: "group/:id/pairs"
   },
   Department: {
      All: "departments",
      GetPairs: "department/pairs"
   },
   Pair: {
      All: "pairs",
      Create: "pair/create",
      Edit: "pair/:id/edit",
      EditList: "professor/:id/pairs/editList"
   },
   Journal: {
      All: "journal"
   },
   NotFound: "404"
}

export const ApiRouteConstants = {
   Account: {
       FindUsersByFIO: "account/FindUsersByFIO/:req",
      GetProfessors: "account/professors",
      SetProfessorDepartment: "account/professor/:userId/setDepartment/:departmentId",
      SetStudentGroup: "account/student/:userId/setGroup/:groupId"
   },
   Authentication: {
      Login: "account/login",
      Registration: "account/registration",
      Role: "account/role",
      User: "account/user"
   },
    Pair: {
        All: "pairs",
        Save: "demo/pair/save",
        Delete: "demo/pair/:id",
        Get: "demo/pair/{id}",
        Proffesor: {
            Get: "demo/professor/:id/pairs",
            Odd: "/professor/{id}/pairs/odd",
            Even: "/professor/{id}/pairs/even",
        },
        Department: {
            Get: "demo/department/:id/pairs",
            Even: "demo/department/:id/pairs/even",
            Odd: "demo/department/:id/pairs/odd",
        },
        Group: {
            Get: "demo/group/:id/pairs",
            Even: "demo/group/:id/pairs/even",
            Odd: "demo/group/:id/pairs/odd",
        },
   },
   News: {
      All: "news/all",
      Last: "news/last",
      Get: "news/get/:id",
      Delete: "news/delete/:id",
      Save: "news/save"
   },
   Task: {
      All: "task/list",
      Create: "task/create",
      Update: "task/update",
      Get: "task/get/:id",
      Delete: "task/delete/:id"
   },
   Journal: {
      All: "jurnal/professor/:professorId/group/:groupId"
   },
   Excel:
       {
          ParseStudyPlan: "excel/ParseStudyPlan"
       },
   Dictonary: {
      Page: {
         Disciplines: "dictionary/discipline",
         Users: "dictionary/user",
         Institutes: "dictionary/institute",
         Departments: "dictionary/department",
         Groups: "dictionary/group",
         Roles: "dictionary/role",
         Rooms: "dictionary/room",
         FieldOfKnowlage: "dictionary/fieldOfKnowledge",
         DayOfWeeks: "dictionary/dayOfWeek",
         Professors: "dictionary/professor",
         WeekTypes: "dictionary/weekType"
      }
   },
   Plugin: {
      All: "plugins"
   }
}