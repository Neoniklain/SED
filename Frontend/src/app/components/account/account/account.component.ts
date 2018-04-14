import {Component, ContentChild, ElementRef, Injectable, NgModule, OnInit, ViewChild} from '@angular/core';
import {NewsDispatcherComponent} from "./news-dispatcher/news-dispatcher.component";
import {Router} from "@angular/router";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {TaskDescListComponent} from "../../task/taskDescList.component";
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

  @ViewChild(NewsDispatcherComponent) newsDispatcherComponent: NewsDispatcherComponent;
  @ViewChild(TaskDescListComponent) taskDescListComponent: TaskDescListComponent;


    constructor(private authenticationService: AuthenticationService,
              private router: Router) {
    this.user = new User();
    this.authenticationService.getUser().subscribe(
        res => {
          this.user = res;
        },
      error => {
        if (error.statusText === "Forbidden")
          this.router.navigate(['/404']);
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
