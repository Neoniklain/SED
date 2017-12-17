import { Http, Response, Headers } from '@angular/http';
import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";

@Injectable()
export class AccountService
{
  private path:string;
  constructor(private http:Http)
  {
    this.path="/api/account/"
  }

  //Тестовые методы для получения и отправки данных. Позже скомуниздю с работы удобный API для этого!
  public LogIn(data?: any, mapJson = true) {
    let uri = this.path + "login";
    console.log("data reg",data);
    if (mapJson) return this.http.post(uri, data, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response));
    else return this.http.post(uri, data, { headers: this.getHeaders() });
  }

  public Register(data?: any, mapJson = true) {
    let uri = this.path + "register";
    if (mapJson) return this.http.post(uri, data, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response).json());
    else return this.http.post(uri, data, { headers: this.getHeaders() });
  }

  private getHeaders(): Headers {
    return new Headers({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }
}
