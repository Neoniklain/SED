import { Component } from '@angular/core';
import {TestService} from "../../core/services/test.service";
import {User} from "../../models/user.model";

@Component({
  selector: 'test',
  templateUrl: './test.component.html'
})

export class TestComponent  {
  public title:string;
  public testMsg:string;
  public resMsg:string;
  public temp:User;
  public user:User;

  constructor(private testService:TestService)
  {
    this.title = 'Valya title for TestComponent';
    this.testMsg = 'Пока данных нет';
    this.resMsg = 'Пока ничего не отправили';
    this.temp=new User();
    this.user=new User();
  }

  //Метод получения данных
  public TestGet()
  {
    this.testService.testGet()
      .subscribe((res: any) => {
          this.temp = res;
          this.testMsg = res.username;
          console.log("Success" + res.username);
        },
        (error: any) => {
          this.testMsg = "Ошибка =( Не удалось получить данные из RestControllera";
          console.error('Error: ' + error);
        });
  }

  //Метод отправки данных
  public TestSet()
  {
    this.testService.testSet(this.user)
      .subscribe((res: any) => {
          this.resMsg = "Success";
          console.log("Success");
        },
        (error: any) => {
          this.resMsg = "Ошибка =( Не удалось отправить данные в RestController";
          console.error('Error: ' + error);
        });
  }
}
