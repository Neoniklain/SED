import {Component, OnInit, OnChanges} from "@angular/core";
import {Router} from "@angular/router";
import {AdminService} from "../../services/admin.service";
import {User} from "../../models/user.model";
import {Discipline} from "../../models/Discipline.model";
import {LazyLoadEvent} from "primeng/api";

@Component({
   selector: 'admin-panel-page',
   templateUrl: "./adminPanel.html",
   styleUrls: ["./adminPanel.css"],
})

export class AdminPanelComponent implements OnInit {
   // Common
   public menuToogle: string;
   // Dictionary
   public users: Array<User>;
   public disciplines: Array<Discipline>;
   public totalRecords: number;

   constructor(private router: Router,
               private adminService: AdminService) {

   }

   ngOnInit() {
      this.menuToogle = "downloadPlan";
      this.users = [];
      this.disciplines = [];
      this.totalRecords = 50;
   }

   setMenuToogle(toogle: string) {
      this.menuToogle = toogle;
      this.updateDictionary();
   }

   updateDictionary(event?: LazyLoadEvent) {
      switch (this.menuToogle) {
         case "userList":
            this.adminService.GetUsers(event).subscribe(
                result => {
                   this.users = result;
                }, error => console.log(error)
            );
         break;
         case "disciplineList":
            this.adminService.GetDisciplines(event).subscribe(
                result => {
                   this.disciplines = result;
                }, error => console.log(error)
            );
            break;
      }

   }

}
