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
    Schedule: {
        All: "schedule",
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
        FindUserByUsername: "account/FindUserByUsername/:req",
        GetProfessors: "account/professors",
        SetProfessorDepartment: "account/professor/:userId/setDepartment/:departmentId",
        SetStudentGroup: "account/student/:userId/setGroup/:groupId",
        GetProfessorByUser: "account/professorByUser/:userId",
        GetStudentByUser: "/studentByUser/:userId",
        GetUserAccessRight: "account/user/:userId/getAccessRight",
        SaveUserAccessRight: "account/user/:userId/saveAccessRight"
    },
    Authentication: {
        Login: "account/login",
        Registration: "account/registration",
        Role: "account/role",
        User: "account/user",
        UserAccessRight: "account/userAccessRight",
        ChangePassword: "account/changePassword",
        ChangePhoto: "account/changePhoto"
    },
    Schedule: {
        All: "pairs",
        Save: "shedule/pair/save",
        Delete: "shedule/pair/:id",
        Get: "shedule/pair/{id}",
        ProffesorPairs: "shedule/professor/:id/pairs",
        DepartmentPairs: "shedule/department/:id/pairs",
        LessonPairs: "shedule/lesson/:id/pairs",
        GroupPairs: "shedule/group/:id/pairs",
        ProffesorLessons: "shedule/professor/:id/lessons",
    },
    News: {
        All: "news/all",
        Last: "news/last",
        Get: "news/get/:id",
        Delete: "news/delete/:id",
        Save: "news/save"
    },
    Task: {
        ListAll: "task/getAll",
        ListExecutor: "task/listExecutor",
        ListCreator: "task/listCreator",
        Create: "task/create",
        AnswerTask: "task/answer",
        ChangeStatusTaskDesc: "task/changeStatusTaskDesc",
        ChangeStatusTaskUser: "task/changeStatusTaskUser",
        GetTaskDesc: "task/getTaskDesc/:id",
        GetTaskUser: "task/getTaskUser/:id",
        UpdateTaskDesc: "task/updateTaskDesc",
        DeleteTask: "task/deleteTask/:id",
        DeleteTaskUser: "task/deleteTaskUser/:id"
    },
    Journal: {
        All: "journal/:lessonId",
        Dates: "journal/dates/:lessonId",
        Save: "journal/save",
        Events: "journal/event/lesson/:lessonId",
        EventSave: "journal/event/save",
        VisitationConfigSave: "journal/visitation/saveConfig",
        VisitationConfigGet: "journal/visitation/lesson/:lessonId",
        EventDelete: "journal/event/delete/:id"
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
            Professors: "dictionary/professor",
            PointType: "dictionary/pointType",
            PairTypes: "dictionary/pairTypes",
        }
    },
    Plugin: {
        All: "plugins"
    },
    File: {
        Download: "file/download/",
        GetFileForObject: "file/getFileForObject/:objectTypeId/:objectId",
        AddFileForObject: "file/addFileForObject/:objectTypeId/:objectId"
    }
}