import { Http, Response, Headers } from '@angular/http';
import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";

@Injectable()
export class TestService
{
  private path:string;
  constructor(private http:Http)
  {
    this.path="/api/test/"
  }

  //Тестовые методы для получения и отправки данных. Позже скомуниздю с работы удобный API для этого!
  testGet() {
    let uri = this.path + "temp";
    var res = this.http.get(uri, { headers: this.getHeaders() }).map(res => <any>res.json());
    return res;
  }

  testSet(data?: any, mapJson = true) {
    let uri = this.path + "set";
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
