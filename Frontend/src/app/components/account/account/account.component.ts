import {Component, ContentChild, ElementRef, Injectable, NgModule, OnInit, ViewChild} from '@angular/core';
import {DocumentComponent} from "./documents/document.component";
import {NewsDispatcherComponent} from "./news-dispatcher/news-dispatcher.component";
import {Router} from "@angular/router";
import {User} from "../../../models/user.model";
import {AuthenticationService} from "../../../services/authService";

@Component({
  selector: 'account-page',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
@Injectable()
export class AccountComponent implements OnInit {

  public user: User;

  @ViewChild(DocumentComponent) documentComponent: DocumentComponent;
  @ViewChild(NewsDispatcherComponent) newsDispatcherComponent: NewsDispatcherComponent;

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
    this.newsDispatcherComponent.toogle();
  }

  menuToole(menuName) {
    if (menuName == "document-page")
      this.documentComponent.Show();
    else
      this.documentComponent.Hide();

    if (menuName == "news-dispatcher-page")
      this.newsDispatcherComponent.Show();
    else
      this.newsDispatcherComponent.Hide();
  }

}
