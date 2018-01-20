import {Component, ContentChild, ElementRef, Injectable, NgModule, OnInit, ViewChild} from '@angular/core';
import {AccountService} from "../../../../core/services/account.service";
import {User} from "../../../../models/user.model";
import {DocumentComponent} from "./documents/document.component";
import {NewsDispatcherComponent} from "./news-dispatcher/news-dispatcher.component";

@Component({
  selector: 'account-page',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
@Injectable()
export class AccountComponent implements OnInit {

  public user:User;

  @ViewChild(DocumentComponent) documentComponent: DocumentComponent;
  @ViewChild(NewsDispatcherComponent) newsDispatcherComponent: NewsDispatcherComponent;

  constructor(private accountService:AccountService)
  {
    this.user=new User();
  }

  ngOnInit(): void {
    this.newsDispatcherComponent.toogle();
    this.accountService.GetUser().subscribe(
      (res: any) => {
        this.user = res;
        console.log("Данные получены",this.user);
      },
      (error: any) => {
        console.error('Error: ' + error);
      });
  }

  private menuToole(menuName)
  {
    if(menuName=="document-page")
      this.documentComponent.Show();
    else
      this.documentComponent.Hide();

    if(menuName=="news-dispatcher-page")
      this.newsDispatcherComponent.Show();
    else
      this.newsDispatcherComponent.Hide();
  }

}
