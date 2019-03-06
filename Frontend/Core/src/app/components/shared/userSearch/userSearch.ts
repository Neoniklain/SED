import {Component, OnInit, Input, Output, EventEmitter} from "@angular/core";
import {User} from "../../../models/account/user.model";
import {NotificationService} from "../../../services/notification.service";
import {AccountService} from "../../../services/accountService";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {LazyLoadEvent} from "primeng/api";
import {DictionaryService} from "../../../services/dictionary.service";
import {DomSanitizer} from "@angular/platform-browser";
import {Role} from "../../../models/account/role.model";

@Component({
   selector: 'user-search-form',
   templateUrl: "./userSearch.html"
})

export class UserSearchComponent implements OnInit {
    // возвращаем выбранных пользователей
    @Output()
    onReturn: EventEmitter<any> = new EventEmitter();
    @Output()
    onClose: EventEmitter<any> = new EventEmitter();
    @Input()
    isModal: boolean = false;
    @Input()
    disabled: boolean = false;
    @Input()
    showReCreateButton: boolean = false;

    public result: User[];
    public _foundedUsers: User[];
    public listOfTypes: any[];
    public Type: any;
    public Dictionary = Dictionary;
    public lastSelected: User;
    public forFilter: LazyLoadEvent;
    public editable: boolean = false;
    public roles: Array<Role>;
    public findRoles: Array<Role>;
    public selectedRole: Role;

    constructor(private accountService: AccountService,
                private notificationService: NotificationService,
                private dictionaryService: DictionaryService,
                public sanitizer: DomSanitizer) {}

    ngOnInit() {
        this.listOfTypes = [];
        this.listOfTypes.push({name: "Всех", value: 0});
        this.listOfTypes.push({name: "По имени", value: 1});
        this.listOfTypes.push({name: "По роли", value: 2});
        this.Type = this.listOfTypes[1];
        this.Clear();
        this.dictionaryService.Get(Dictionary.roles).subscribe(result => { this.roles = result.content; });
    }

    public Clear() {
        this.result = [];
        this.forFilter = {
            rows: 0,
            first: 0,
            globalFilter: ""
        };
        this.lastSelected = new User();
    }

    public searchUserByFIO(event: any) {
        let query = event.query.substring(0, 60);
        this.accountService.FindUsersByFIO(query)
            .subscribe((res) => {
                    this._foundedUsers = res.data;
                },
                (error: any) => {
                    console.error("Ошибка" + error);
                });
    }

    public AddUser(user: User) {
        if (!this.result.find(function (x) {
            return x.id == user.id;
        })) {
            this.result.push(user);
        }
        this.lastSelected = null;
    }

    removeUser(user: User) {
        this.result.splice(this.result.indexOf(user), 1);
    }

    public ReCreate() {
        this.Clear();
        this.editable = true;
    }

    // выбрана кнопка применить в фильтре
    public returnResult() {
        this.lastSelected = new User();
        // Если выбрано значение "Все пользователи"
        if (this.Type.value == 0) {
            this.dictionaryService.Get(Dictionary.users, this.forFilter)
                .subscribe(
                    res => {
                        this.result = res.content;
                        this.onReturn.emit(this.result);
                        this.editable = false;
                    }
                );
        }
        // Если выбрано значение "По роли"
        else if (this.Type.value == 2) {
            this.accountService.FindUsersByRoleName(this.selectedRole.roleName)
                .subscribe(
                    res => {
                        this.result = res.data;
                        this.onReturn.emit(this.result);
                        this.editable = false;
                    }
                );
        }
        else {
            this.onReturn.emit(this.result);
            this.editable = false;
        }
    }

    public closeForm() {
        this.editable = false;
        this.onClose.emit();
    }

    public Edit(users: User[]) {
        this.editable = true;
        this.result = users;
    }

    public searchRoles(event: any) {
        let query = event.query.substring(0, 60);
        this.findRoles = this.roles;
        let newRoles: Array<Role> = new Array();
        for (let role of this.roles){
            if (role.roleName.toLowerCase().indexOf(query.toLowerCase()) != -1) {
                newRoles.push(role);
            }
        }
        if (newRoles.length > 0)
            this.findRoles = newRoles;
        else
            this.findRoles = [];
    }

    public isShowUserList(): boolean {
        if (this.result == null) {
            return false;
        }
        if (this.Type.value == 0) {
            return false;
        }
        return true;
    }
}