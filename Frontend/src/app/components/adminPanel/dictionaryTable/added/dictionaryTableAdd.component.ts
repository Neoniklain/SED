import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {isUndefined} from "util";
import {Dictionary} from "../../../../models/admin/dictionary.model";
import {DictionaryService} from "../../../../services/dictionary.service";
import {Role} from "../../../../models/account/role.model";

@Component({
   selector: 'dictionary-table-add',
   templateUrl: "./dictionaryTableAdd.component.html",
   styleUrls: ["./dictionaryTableAdd.component.css"],
})

export class DictionaryTableAddComponent implements OnInit {

   @Input() type;
   public columnsName: string[];
   public roles: Array<Role>;

   constructor(private router: Router,
               private dictionaryService: DictionaryService) { }

   ngOnInit() {
      switch (this.type) {
         case Dictionary[1]:
            this.dictionaryService.GetRoles().subscribe(result => { this.roles = result.content; console.log(this.roles); });
            break;
      }
   }



}
