import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {isDate, isObject} from "util";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../../services/dictionary.service";

@Component({
selector: 'dictionary',
templateUrl: "./dictionaryTable.component.html",
styleUrls: ["./dictionaryTable.component.css"],
})

export class DictionaryComponent implements OnInit, OnChanges {

    @Input() type: Dictionary;

    public columnsName: string[];
    public model;
    public deleteModel;
    public updateTableTrigger: boolean = false;
    public editMode: boolean = false;
    public deleteMode: boolean = false;
    public Dictionary;
    public data: PageResult;
    public event: LazyLoadEvent;

    constructor(private router: Router,
               private dictionaryService: DictionaryService) { }

    ngOnInit(): void {
      this.Dictionary = Dictionary;
    }

    ngOnChanges(): void {
      this.model = null;
    }

    // Редактирование модели
    edit(item) {
        this.model = item;
        this.editMode = true;
    }
    // Удаление модели
    delete(item) {
        this.model = item;
        this.deleteMode = true;
    }

    // Завершение редактировния модели
    canelEditableModel() {
        this.model = null;
        this.editMode = false;
        this.deleteMode = false;
    }

    updateTable() {
        this.updateTableTrigger = true;
    }
    tableUpdated() {
        this.updateTableTrigger = false;
    }
}
