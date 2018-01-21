import { Http, Response, Headers } from '@angular/http';
import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";

@Injectable()
export class IssueService
{
  private path:string;
  constructor(private http:Http)
  {
    this.path="/api/issue/"
  }

  public Get(id) {
    return this.http.post(this.path + "get/"+ id, { headers: this.getHeaders() }).map(res => <Response>res.json());
  }

  public Delete(id) {
    return this.http.post(this.path + "delete/"+ id, { headers: this.getHeaders() }).map(res => <Response>res);
  }

  public GetList() {
    let uri = this.path + "list";
    return this.http.get(uri, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response.json()));
  }

  public Create(data?: any) {
    let uri = this.path + "create";
    return this.http.post(uri, data, { headers: this.getHeaders() })
      .map(response => <any>(<Response>response));
  }

  private getHeaders(): Headers {
    let token = localStorage.getItem('token');
    let headers = new Headers({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    if(token)
      headers.append('Authorization', token);
    return headers;
  }
}
