import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {isUndefined} from "util";

@Component({
   selector: 'dictionary-table-add',
   templateUrl: "./dictionaryTableAdd.component.html",
   styleUrls: ["./dictionaryTableAdd.component.css"],
})

export class DictionaryTableAddComponent implements OnInit {

   public columnsName: string[];

   constructor(private router: Router) { }

   ngOnInit() {}



}
