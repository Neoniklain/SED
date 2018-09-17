import {Component, OnInit, Input, Output, EventEmitter} from "@angular/core";
import {User} from "../../../models/account/user.model";
import {NotificationService} from "../../../services/notification.service";
import {AccountService} from "../../../services/accountService";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {LazyLoadEvent} from "primeng/api";
import {DictionaryService} from "../../../services/dictionary.service";
import {DomSanitizer} from "@angular/platform-browser";

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

    constructor(private accountService: AccountService,
                private notificationService: NotificationService,
                private dictionaryService: DictionaryService,
                public sanitizer: DomSanitizer) {}

    ngOnInit() {
        this.listOfTypes = [];
        this.listOfTypes.push({name: "Всех", value: 0});
        this.listOfTypes.push({name: "Найти по имени", value: 1});
        this.Type = this.listOfTypes[1];
        this.Clear();
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
       this.result.push(user);
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
        // Если выбрано значение "Все пользователи)
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
}