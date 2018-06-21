import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {Roles} from "../../../models/account/role.model";

@Component({
  selector: 'account-page',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
@Injectable()
export class AccountComponent implements OnInit {

  public user: User;
  public menuName: string;
  public Roles;

    constructor(private authenticationService: AuthenticationService,
              private router: Router) {
    this.user = new User();
    this.authenticationService.getUser().subscribe(
        res => {
          this.user = res.data;
        });
  }

  ngOnInit(): void {
    this.Roles = Roles;
    this.menuName = "news-dispatcher-page";
  }

  menuToole(menuName) {
    this.menuName = menuName;
  }

}
