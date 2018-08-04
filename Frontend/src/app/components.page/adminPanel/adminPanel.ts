import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../services/dictionary.service";
import {Dictionary} from "../../models/admin/dictionary.model";
import {User, UserCreate} from "../../models/account/user.model";
import {Discipline} from "../../models/shedule/discipline";
import {Institute} from "../../models/shedule/institute";
import {Department} from "../../models/shedule/department";
import {Group} from "../../models/shedule/group";
import {isUndefined} from "util";
import {Room} from "../../models/shedule/room.model";

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
          case Dictionary.rooms.toString():
              this.currentModel = new Room();
              break;
      }
   }

   isDictionary() {
         return !isUndefined(this.Dictionary[this.menuToogle]);
   }

}