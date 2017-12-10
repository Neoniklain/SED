import { Http, Response, Headers } from '@angular/http';
import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";

@Injectable()
export class NewsService
{
  private path:string;
  constructor(private http:Http)
  {
    this.path="/api/news/"
  }

  //Тестовые методы для получения и отправки данных. Позже скомуниздю с работы удобный API для этого!
  public GetAll() {
    return this.http.get(this.path + "getall", { headers: this.getHeaders() }).map(res => <any>res.json());
  }

  public Get(id) {
    return this.http.post(this.path + "get/"+ id, { headers: this.getHeaders() }).map(res => <Response>res.json());
  }

  public Delete(id) {
    return this.http.post(this.path + "delete/"+ id, { headers: this.getHeaders() }).map(res => <Response>res);
  }

  public GetLast() {
    return this.http.get(this.path + "getlast", { headers: this.getHeaders() }).map(res => <any>res.json());
  }

  public Save(data?: any, mapJson = true) {
    let uri = this.path + "save";
    if (mapJson) return this.http.post(uri, data, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response));
    else return this.http.post(uri, data, { headers: this.getHeaders() });
  }

  private getHeaders(): Headers {
    return new Headers({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }
}
