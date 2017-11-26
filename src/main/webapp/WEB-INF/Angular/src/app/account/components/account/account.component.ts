import { Component } from '@angular/core';
import {AccountService} from "../../../core/services/account.service";
import {User} from "../../../models/user.model";

@Component({
  selector: 'account-page',
  templateUrl: './account.component.html'
})

export class AccountComponent  {
  public user:User;

  constructor(private accountService:AccountService)
  {
    this.user=new User();
    this.user.username="Валя/Виталя";
    this.user.email="SED@kemsu.ru";
  }
}
