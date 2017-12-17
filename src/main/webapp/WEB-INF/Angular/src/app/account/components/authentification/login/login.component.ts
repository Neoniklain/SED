import { Component } from '@angular/core';
import {AccountService} from "../../../../core/services/account.service";
import {LogInUser, User} from "../../../../models/user.model";
import { Router } from '@angular/router';

@Component({
  selector: 'login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LogInComponent  {
  public user:LogInUser;
  public auth_user:User;

  constructor(
    private accountService:AccountService,
    private router:Router
  )
  {
    this.user=new LogInUser();
    this.auth_user=new User();
  }

  public LogIn()
  {
    this.accountService.LogIn(this.user)
      .subscribe((res:any)=> {
      console.info("token",res.headers.get("Authorization"));
        let token = res.headers.get("Authorization");
        if(token!="") {
          console.log("Авторизация успешна, получен токен: "+token);
          localStorage.setItem("token",token);
          //this.router.navigate(['/account/account']);
        }
        else {
          console.log("Авторизация не успешна");
        }
      },
      (error:any)=> {
        console.log("Ошибка авторизации: "+error);
      });
  }
}
