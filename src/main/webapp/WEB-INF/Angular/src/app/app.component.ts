import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  private temp:Date = new Date();
  public curYear:any = this.temp.getFullYear();
}
