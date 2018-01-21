import {
  Component, OnInit, OnChanges, SimpleChanges, DoCheck, AfterContentChecked, AfterContentInit,
  AfterViewChecked, AfterViewInit
} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit, DoCheck
   {
  accountService: any;
  private temp:Date = new Date();
  public curYear:any = this.temp.getFullYear();
  public isAuthoried:boolean;
  public role:string;

  constructor(private router: Router)
  {
  }

  ngOnInit(): void {
  }

  logout(): void {
    localStorage.removeItem("token");
    this.isAuthoried=false;
    this.router.navigate(['/news']);
  }

  ngDoCheck() {
    let token = localStorage.getItem("token");
    token == null ? this.isAuthoried=false : this.isAuthoried=true;
  }

}
