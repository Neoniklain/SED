import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";

@Component({
   selector: 'dictionary-table',
   templateUrl: "./dictionaryTable.component.html",
   styleUrls: ["./dictionaryTable.component.css"],
})

export class DictionaryTableComponent implements OnInit, OnChanges {

   @Input() data;
   @Input() header;
   @Input() totalRecords;
   @Output() loadData = new EventEmitter();
   public columnsName: string[];

   constructor(private router: Router) { }

   ngOnInit(): void {
      this.fillColumn();
   }

   ngOnChanges(): void {
      this.fillColumn();
   }

   fillColumn() {
      if (this.data && this.data.length > 0) this.columnsName = Object.keys(this.data[0]);
      else this.data = [];
   }

   isArray(obj: any ) {
      return Array.isArray(obj);
   }

   getKeys(obj: any ) {
      return Object.keys(obj);
   }

   loadLazy(event: LazyLoadEvent) {
      this.loadData.emit(event);
   }

}
