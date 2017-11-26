import { Component } from '@angular/core';
import {AccountService} from "../../../core/services/account.service";
import {LogInUser, User} from "../../../models/user.model";

@Component({
  selector: 'login-page',
  templateUrl: './login.component.html'
})

export class LogInComponent  {
  public user:LogInUser;
  public auth_user:User;

  constructor(private accountService:AccountService)
  {
    this.user=new LogInUser();
  }

  public LogIn()
  {
    this.accountService.LogIn(this.user)
      .subscribe((res:any)=>
      {
        this.auth_user = res;
        console.log("Авторизация успешна");
      },
        (error:any)=>
        {
          console.log("Ошибка авторизации");
        });
  }
}
