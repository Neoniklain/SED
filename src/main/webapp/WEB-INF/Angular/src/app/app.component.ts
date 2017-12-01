import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  private temp:Date = new Date();
  public curYear:any = this.temp.getFullYear();
  constructor(
    private router: Router)
  {

  }
}
