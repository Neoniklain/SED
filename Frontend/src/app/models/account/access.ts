import {User} from "./user.model";

export class UserAccessRight {
    public user: User;
    public rights: Array<UserAccessRow>;

    constructor() {
        this.user = new User();
        this.rights = new Array<UserAccessRow>();
    }
}

export class UserAccessRow {
    public right: AccessRight;
    public allow: boolean;

    constructor() {
        this.right = new AccessRight();
        this.allow = false;
    }
}

export class AccessRight {
    public id: number;
    public name: String;
    public header: String;
    public caption: String;

    constructor() {
        this.id = 0;
        this.name = "";
        this.header = "";
        this.caption = "";
    }
}

export enum AccessRightType {
    CREATE_OR_UPDATE_NEWS = <any>"CREATE_OR_UPDATE_NEWS",
    CREATE_TASK = <any>"CREATE_TASK",
    VIEW_TASK = <any>"VIEW_TASK",
    VIEW_SCHEDULE= <any>"VIEW_SCHEDULE",
    VIEW_JOURNAL= <any>"VIEW_JOURNAL",
    EDIT_JOURNAL= <any>"EDIT_JOURNAL",
    EDIT_LESSONS= <any>"EDIT_LESSONS",
}
