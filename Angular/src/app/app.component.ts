import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  accountService: any;
  private temp:Date = new Date();
  public curYear:any = this.temp.getFullYear();
  public isAuthoried:boolean;
  public role:string;

  constructor(private router: Router)
  {
    this.router.navigate(["/news/news"]);
  }

  ngOnInit(): void {
    localStorage.getItem("token") !== "undifiend" ? this.isAuthoried=true : this.isAuthoried=false;
  }

  logout(): void {
    localStorage.removeItem("token");
    this.isAuthoried=false;
  }
}
