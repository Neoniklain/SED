import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {LogInUser, User} from "../../../../models/user.model";
import {AuthenticationService} from "../../../../services/authService";

@Component({
  selector: 'login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LogInComponent  {
  public user: LogInUser;
  public auth_user: User;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.user = new LogInUser();
    this.auth_user = new User();
  }

  public LogIn() {
    this.authenticationService.login(this.user).subscribe(
      res => {
          if (res.token) {
              this.authenticationService.setToken(res.token);
              this.authenticationService.handleAuth();
          }
      },
      err => { console.error(err); }
    );
  }
}
