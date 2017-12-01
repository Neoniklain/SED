import { Component } from '@angular/core';
import {News} from "../../../models/news.model";
import {NewsService} from "../../../core/services/news.service";

@Component({
  selector: 'list-news-page',
  templateUrl: './listNews.component.html'
})

export class ListNewsComponent  {
public _listOfNews:News[];
  constructor(
    private newsService:NewsService)
  {
    this._listOfNews = [];
    this.GetNews();
  }

  public GetNews()
  {
    this.newsService.GetAllNews()
      .subscribe((res: any) => {
          this._listOfNews = res;
          console.log("Данные получены");
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }
}
