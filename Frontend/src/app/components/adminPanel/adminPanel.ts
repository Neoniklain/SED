import {Component, OnInit, OnChanges} from "@angular/core";
import {Router} from "@angular/router";
import {User} from "../../models/account/user.model";
import {Discipline} from "../../models/discipline.model";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../services/dictionary.service";
import {Dictionary} from "../../models/admin/dictionary.model";

@Component({
   selector: 'admin-panel-page',
   templateUrl: "./adminPanel.html",
   styleUrls: ["./adminPanel.css"],
})

export class AdminPanelComponent implements OnInit {
   // Common
   public menuToogle: string;
   // Dictionary
   public currentModel: PageResult;

   public Dictionary;

   constructor(private router: Router,
               private dictionaryService: DictionaryService) {}

   ngOnInit() {
      this.menuToogle = "downloadPlan";
      this.currentModel = new PageResult();
      this.Dictionary = Dictionary;
   }

   setMenuToogle(toogle: string) {
      this.menuToogle = toogle;
      this.updateDictionary();
   }

   updateDictionary(event?: LazyLoadEvent) {
      switch (this.menuToogle) {
         case Dictionary[1]:
            this.dictionaryService.GetUsers(event).subscribe(result => { this.currentModel = result; });
         break;
         case Dictionary[2]:
            this.dictionaryService.GetDisciplines(event).subscribe(result => {
               console.log("this.currentModel", this.currentModel);
               this.currentModel = result; });
         break;
         case Dictionary[3]:
            this.dictionaryService.GetInstitutes(event).subscribe(result => { this.currentModel = result; });
            break;
         case Dictionary[4]:
            this.dictionaryService.GetDepartments(event).subscribe(result => { this.currentModel = result; });
            break;
         case Dictionary[5]:
            this.dictionaryService.GetGroups(event).subscribe(result => { this.currentModel = result; });
            break;
      }

   }

   isDictionary() {
         return Object.keys(this.Dictionary).indexOf(this.menuToogle)!=-1;
   }

}