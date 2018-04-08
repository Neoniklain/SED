import { Component } from '@angular/core';
import {LogInUser} from "../../../../models/account/user.model";
import {AuthenticationService} from "../../../../services/authService";
import {MessageService} from "primeng/components/common/messageservice";
import {Router} from "@angular/router";
import {RouteConstants} from "../../../../bootstrap/app.route.constants";

@Component({
  selector: 'register-page',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent  {
  public user: LogInUser;
  public PassConfirm: string;
  public msgs: string;

    constructor(
      private authenticationService: AuthenticationService,
      private router: Router,
      private messageService: MessageService
    ) {
        this.user = new LogInUser();
    }

    public Register() {
    if (this.PassConfirm === this.user.password) {
      this.authenticationService.register(this.user).subscribe(
          result => {
              if (result.status === "ok") {
                  this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Регистрация выполненна успешно!'});
                  setTimeout(() => {
                      this.router.navigate([RouteConstants.Account.Login]);
                  }, 1500);
              }
          },
          error => console.log(error),
      );
    }
    else
        this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Пароли должны совпадать!'});
    }
}
