import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {isDate, isObject, isUndefined} from "util";

@Component({
   selector: 'dictionary-table',
   templateUrl: "./dictionaryTable.component.html",
   styleUrls: ["./dictionaryTable.component.css"],
})

export class DictionaryTableComponent implements OnInit, OnChanges {

   @Input() data: PageResult;
   @Input() type;
   @Output() loadData = new EventEmitter();

   public columnsName: string[];
   public editableModel;
   public deleteModel;
   public editMode: boolean = false;


   constructor(private router: Router) { }

   ngOnInit(): void {
      this.fillColumn();
   }

   ngOnChanges(): void {
      this.editableModel = null;
      this.deleteModel = null;
      this.fillColumn();
   }

   fillColumn() {
      if (this.data) {
         if (!isUndefined(this.data.content) && this.data.content.length > 0)
            this.columnsName = Object.keys(this.data.content[0]);
         else
            this.data.content = [];
      }
   }

   edit(item) {
      this.editableModel = item;
      this.editMode = true;
   }
   delete(item) {
      this.deleteModel = item;
   }

   canelEditableModel() {
      this.editableModel = null;
      this.editMode = false;
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
      this.loadData.emit();
   }


}
