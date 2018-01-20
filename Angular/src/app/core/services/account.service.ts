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
  public LogIn(data?: any) {
    let uri = this.path + "login";
    return this.http.post(uri, data, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response));
  }

  public Register(data?: any) {
    let uri = this.path + "register";
    return this.http.post(uri, data, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response).json());
  }

  public GetRole() {
    let uri = this.path + "register";
    return this.http.get(uri, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response).json());
  }

  private getHeaders(): Headers {
    return new Headers({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }
}
