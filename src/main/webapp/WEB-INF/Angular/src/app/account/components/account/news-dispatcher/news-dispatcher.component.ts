import {Component, Input} from '@angular/core';
import {NewsService} from "../../../../core/services/news.service";
import {News} from "../../../../models/news.model";

@Component({
  selector: 'news-dispatcher-page',
  templateUrl: './news-dispatcher.component.html'
})

export class NewsDispatcherComponent{

  private show:boolean = false;

  public _lastNews:News;
  constructor(private newsService:NewsService)
  {
    this.newsService.GetLastNews()
      .subscribe((res: any) => {
          this._lastNews = res;
          console.log("Данные получены");
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }

  ngOnInit(): void {

  }

  public Show() {
      this.show = true;
  }
  public Hide() {
    this.show = false;
  }
  public toogle() {
    if(this.show==false)
      this.show=true;
    else
      this.show=false;
  }

}
