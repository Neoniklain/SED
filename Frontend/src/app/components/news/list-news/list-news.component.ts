import { Component } from '@angular/core';
import {News} from "../../../models/news/news.model";
import {NewsService} from "../../../services/news.service";

@Component({
  selector: 'list-news-page',
  templateUrl: './list-news.component.html'
})

export class ListNewsComponent  {

public _listOfNews: News[];

  constructor(private newsService: NewsService)
  {
    this._listOfNews = [];
    this.GetNews();
  }

  public GetNews() {
    this.newsService.GetAll()
      .subscribe((res: any) => {
          this._listOfNews = res;
        },
        error => console.error(error));
  }
}
