import {Component, OnInit, OnChanges} from "@angular/core";
import {Router} from "@angular/router";
import {User} from "../../models/account/user.model";
import {Discipline} from "../../models/discipline.model";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../services/dictionary.service";

@Component({
   selector: 'admin-panel-page',
   templateUrl: "./adminPanel.html",
   styleUrls: ["./adminPanel.css"],
})

export class AdminPanelComponent implements OnInit {
   // Common
   public menuToogle: string;
   // Dictionary
   public users: PageResult;
   public disciplines: PageResult;
   public institute: PageResult;
   public department: PageResult;
   public group: PageResult;

   constructor(private router: Router,
               private dictionaryService: DictionaryService) {

   }

   ngOnInit() {
      this.menuToogle = "downloadPlan";
      this.users = new PageResult();
      this.disciplines = new PageResult();
   }

   setMenuToogle(toogle: string) {
      this.menuToogle = toogle;
      this.updateDictionary();
   }

   updateDictionary(event?: LazyLoadEvent) {
      switch (this.menuToogle) {
         case "userList":
            this.dictionaryService.GetUsers(event).subscribe(result => { this.users = result; });
         break;
         case "disciplineList":
            this.dictionaryService.GetDisciplines(event).subscribe(result => { this.disciplines = result; });
         break;
         case "instituteList":
            this.dictionaryService.GetInstitutes(event).subscribe(result => { this.institute = result; });
            break;
         case "departmentList":
            this.dictionaryService.GetDepartments(event).subscribe(result => { this.department = result; });
            break;
         case "groupList":
            this.dictionaryService.GetGroups(event).subscribe(result => { this.group = result; });
            break;
      }

   }

}
