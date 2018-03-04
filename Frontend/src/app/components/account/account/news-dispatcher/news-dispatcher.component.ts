import { Component, Input } from '@angular/core';
import { News } from "../../../../models/news/news.model";
import { NewsService } from "../../../../services/news.service";
import { RouteConstants } from "../../../../bootstrap/app.route.constants";

@Component({
  selector: 'news-dispatcher-page',
  templateUrl: './news-dispatcher.component.html',
  styleUrls: ['./news-dispatcher.component.css']
})

export class NewsDispatcherComponent {

  public show: boolean = false;
  public RouteConstants = RouteConstants;
  public _lastNews: News;
  constructor(private newsService: NewsService)
  {
    this._lastNews = new News();
    this.newsService.GetLast()
      .subscribe((res: any) => {
          this._lastNews = res;
          console.log("Данные получены");
        },
        (error: any) => {
          console.error('Error222: ' + error);
        });
  }

  ngOnInit(): void {

  }

}
