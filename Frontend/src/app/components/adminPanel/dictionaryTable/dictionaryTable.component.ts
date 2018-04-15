import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {isDate, isObject} from "util";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../../services/dictionary.service";

@Component({
selector: 'dictionary-table',
templateUrl: "./dictionaryTable.component.html",
styleUrls: ["./dictionaryTable.component.css"],
})

export class DictionaryTableComponent implements OnInit, OnChanges {

    @Input() model: any;
    @Input() type;
    @Output() loadData = new EventEmitter();

    public columnsName: string[];
    public editableModel;
    public deleteModel;
    public editMode: boolean = false;
    public Dictionary;
    public data: PageResult;

    constructor(private router: Router,
               private dictionaryService: DictionaryService) { }

    ngOnInit(): void {
      this.Dictionary = Dictionary;
      this.getData();
    }

    ngOnChanges(): void {
      this.editableModel = null;
      this.deleteModel = null;
      this.getData();
    }

    // Получить данные
    getData() {
      this.columnsName = Object.keys(this.model);
       switch (this.type.toString()) {
           case Dictionary.users.toString():
               this.dictionaryService.GetUsers(event).subscribe(result => { this.data = result; });
               break;
           case Dictionary.disciplines.toString():
               this.dictionaryService.GetDisciplines(event).subscribe(result => { this.data = result; });
               break;
           case Dictionary.institutes.toString():
               this.dictionaryService.GetInstitutes(event).subscribe(result => { this.data = result; });
               break;
           case Dictionary.departments.toString():
               this.dictionaryService.GetDepartments(event).subscribe(result => { this.data = result; });
               break;
           case Dictionary.groups.toString():
               this.dictionaryService.GetGroups(event).subscribe(result => { this.data = result; });
               break;
           case Dictionary.rooms.toString():
               this.dictionaryService.GetRooms(event).subscribe(result => { this.data = result; });
               break;
       }
    }
    // Редактирование модели
    edit(item) {
        this.editableModel = item;
        this.editMode = true;
    }
    // Удаление модели
    delete(item) {
      this.deleteModel = item;
    }
    // Завершение редактировния модели
    canelEditableModel() {
      this.editableModel = null;
      this.editMode = false;
        this.loadData.emit();
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
    loadLazy(event: LazyLoadEvent) {
      this.loadData.emit(event);
    }
    onLoadData() {
      this.getData();
}
}
