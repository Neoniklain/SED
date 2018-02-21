import {Component, OnInit, OnChanges} from "@angular/core";
import {Router} from "@angular/router";
import {AdminService} from "../../services/admin.service";
import {User} from "../../models/user.model";
import {Discipline} from "../../models/Discipline.model";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../models/admin/PageResult.model.list";

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

   constructor(private router: Router,
               private adminService: AdminService) {

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
            this.adminService.GetUsers(event).subscribe(
                result => {
                   this.users = result;
                   console.log(this.users);
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
