import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../services/dictionary.service";
import {Dictionary} from "../../models/admin/dictionary.model";
import {User, UserCreate} from "../../models/account/user.model";
import {Discipline} from "../../models/discipline";
import {Institute} from "../../models/institute";
import {Department} from "../../models/department";
import {Group} from "../../models/group";
import {isUndefined} from "util";

@Component({
   selector: 'admin-panel-page',
   templateUrl: "./adminPanel.html",
   styleUrls: ["./adminPanel.css"],
})

export class AdminPanelComponent implements OnInit {
   // Common
   public menuToogle: string;
   // Dictionary
   public currentModel: any;

   public Dictionary;

   constructor(private router: Router,
               private dictionaryService: DictionaryService) {}

   ngOnInit() {
      this.menuToogle = "downloadPlan";
      this.Dictionary = Dictionary;
   }

   setMenuToogle(toogle: string) {
      this.menuToogle = toogle;
      this.updateDictionary();
   }

   updateDictionary(event?: LazyLoadEvent) {
      switch (this.menuToogle.toString()) {
         case Dictionary.users.toString():
            this.currentModel = new User();
         break;
         case Dictionary.disciplines.toString():
             this.currentModel = new Discipline();
         break;
         case Dictionary.institutes.toString():
             this.currentModel = new Institute();
            break;
         case Dictionary.departments.toString():
             this.currentModel = new Department();
            break;
         case Dictionary.groups.toString():
             this.currentModel = new Group();
            break;
      }
   }

   isDictionary() {
         return !isUndefined(this.Dictionary[this.menuToogle]);
   }

}