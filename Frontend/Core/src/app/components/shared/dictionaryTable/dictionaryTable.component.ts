import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {isDate, isObject, isUndefined} from "util";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../../services/dictionary.service";
import {Institute} from "../../../models/shedule/institute";
import {Department} from "../../../models/shedule/department";
import {User} from "../../../models/account/user.model";
import {Discipline} from "../../../models/shedule/discipline";
import {Room} from "../../../models/shedule/room.model";
import {Group} from "../../../models/shedule/group";
import {MoodleService} from "../../../services/moodle.service";
import {NotificationService} from "../../../services/notification.service";

@Component({
selector: 'dictionary-table',
templateUrl: "./dictionaryTable.component.html",
styleUrls: ["./dictionaryTable.component.css"],
})

export class DictionaryTableComponent implements OnInit, OnChanges {

    @Input() type: Dictionary;
    /**
     * Переменная для обновления таблицы. Присвоить true, чтобы обновить.
     */
    @Input() updateTable: boolean = false;
    @Output() tableUpdated = new EventEmitter();

    @Input() editable: boolean;
    @Output() clickOnEdit = new EventEmitter();
    @Output() clickOnDelete = new EventEmitter();

    @Input() clickableRow: boolean;
    @Output() clickOnRow = new EventEmitter()

    @Input() loading: boolean = true;

    public model: any;

    public columnsName: string[];
    public Dictionary = Dictionary;
    public data: PageResult;
    public event: LazyLoadEvent;

    constructor(private router: Router,
                private dictionaryService: DictionaryService,
                private moodleService: MoodleService,
                private notificationService: NotificationService) { }

    ngOnInit(): void {
        this.getData(this.event);
    }

    ngOnChanges(): void {
        this.updateModel();
        this.getData(this.event);
    }

    updateModel() {
        this.loading = true;
        this.model = this.dictionaryService.CreateInstance(this.type);
        }

    // Получить данные
    getData(event?) {
        this.columnsName = Object.keys(this.model);
        this.dictionaryService.Get(this.type, event).subscribe(result => {
            this.loading = false;
            this.data = result;
            this.updateTable = false;
            this.tableUpdated.emit();
        });
    }

    // Редактирование модели
    edit(item) {
        this.clickOnEdit.emit(item);
    }

    // Удаление модели
    delete(item) {
        this.clickOnDelete.emit(item);
    }

    config(item) {
        this.clickOnRow.emit(item);
    }

    isArray(obj: any ) {
      return Array.isArray(obj);
    }

    itIsObject(obj: any ) {
      return isObject(obj);
    }

    getKeys(obj: any ) {
      return Object.keys(obj);
    }

    dateis(obj: any) {
      isDate(obj);
    }

    isAbleToRegisterMoodle(): boolean {
        let res = false;
        switch (this.type.toString()) {
            case Dictionary.users.toString():
                res = true;
                break;
            case Dictionary.groups.toString():
                res = true;
                break;
        }
        return res;
    }

    registerMoodle(obj: any) {
        switch (this.type.toString()) {
            case Dictionary.users.toString():
                let user = <User>obj;
                this.moodleService.CreateUser(user.id).subscribe(
                    res => {
                        this.notificationService.FromStatus(res);
                    },
                    error => {
                        console.error("Moodle Create User", error);
                    }
                );
                break;
            case Dictionary.groups.toString():
                let group = <Group>obj;
                this.moodleService.CreateStudentsOfGroup(group.id).subscribe(
                    res => {
                        this.notificationService.FromStatus(res);
                    },
                    error => {
                        console.error("Moodle Create Students of Group", error);
                    }
                );
                break;
        }
    }

    loadLazy(event: LazyLoadEvent) {
        this.event = event;
        this.getData(this.event);
    }

}
