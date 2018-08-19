import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/account/user.model";
import {AuthenticationService} from "../../services/authService";
import {Roles} from "../../models/account/role.model";
import {AccessRightType} from "../../models/account/access";

@Component({
  selector: 'account-page',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
@Injectable()
export class AccountComponent implements OnInit {
    public user: User;
    public menuName: string;
    public AccessRightType;
    public Roles;
    public viewMenu: boolean = true;

    constructor(private authenticationService: AuthenticationService,
                private router: Router) {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
          res => {
            this.user = res.data;
              if (this.user.photo === "")
                  this.user.photo = "images/anon-user.jpg";
          });
     }

    ngOnInit(): void {
        this.AccessRightType = AccessRightType;
        this.Roles = Roles;
        this.menuName = "news-dispatcher-page";
    }

    toogleViewMenu() {
        this.viewMenu = !this.viewMenu;
    }

    menuToole(menuName) {
      this.menuName = menuName;
    }

}
