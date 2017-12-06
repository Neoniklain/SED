import {Component, ContentChild, ElementRef, NgModule, ViewChild} from '@angular/core';
import {AccountService} from "../../../core/services/account.service";
import {User} from "../../../models/user.model";
import {DocumentComponent} from "../documents/document.component";

@Component({
  selector: 'account-page',
  templateUrl: './account.component.html'
})
export class AccountComponent  {
  public user:User;

  @ViewChild(DocumentComponent) documentComponentShow: DocumentComponent;

  constructor(private accountService:AccountService)
  {
    console.log(this.documentComponentShow.getShow);

    this.user=new User();
    this.user.username="Валя/Виталя";
    this.user.email="SED@kemsu.ru";
  }
}
