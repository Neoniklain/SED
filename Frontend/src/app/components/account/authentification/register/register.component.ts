import { Component } from '@angular/core';
import {LogInUser} from "../../../../models/user.model";
import {AuthenticationService} from "../../../../services/authService";

@Component({
  selector: 'register-page',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent  {
  public user: LogInUser;
  public PassConfirm: string;

  constructor(private authenticationService: AuthenticationService)
  {
    this.user = new LogInUser();
  }

  public Register()
  {
    if (this.PassConfirm == this.user.password)
      alert("Регистрация User: " + this.user.username + ", E-Mail: " + this.user.email + ", Pass: " + this.user.password);
    else
      alert("Проверьте корректность пароля");
  }
}
