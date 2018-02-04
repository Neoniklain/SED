import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {LogInUser, User} from "../../../../models/user.model";
import {MessageService} from "primeng/components/common/messageservice";
import {AuthenticationService} from "../../../../services/authService";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'login-page',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LogInComponent  {
    public user: LogInUser;
    public auth_user: User;
    public error: string;
    public tryed: boolean;

    constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private messageService: MessageService
    ) {
    this.user = new LogInUser();
    this.auth_user = new User();
    this.tryed = false;
    }

    public LogIn(form: NgForm) {
        console.log(form);
        if (form.invalid) {
            form.submitted;
            return;
        }
        this.authenticationService.login(this.user).subscribe(
          res => {
              if (res.token) {
                  this.authenticationService.setToken(res.token);
                  this.authenticationService.handleAuth();
              }
          },
          err => {
              console.log(err);
              if (err.status === 401) {
                  this.error = "Не верный логин пароль.";
              }
          }
        );
    }
}
