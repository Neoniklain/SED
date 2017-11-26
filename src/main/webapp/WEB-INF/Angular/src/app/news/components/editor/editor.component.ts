import { Component } from '@angular/core';
import {NewNews} from "../../../models/news.model";
import {NewsService} from "../../../core/services/news.service";

@Component({
  selector: 'editor-page',
  templateUrl: './editor.component.html'
})

export class EditorComponent  {
public newNews:NewNews;
  constructor(
    private newsService:NewsService)
  {
    this.newNews = new NewNews();
  }

  public SaveNews()
  {
    this.newsService.SaveNews(this.newNews)
      .subscribe((res: any) => {
          console.log("Новость добавлена");
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }

  public ClearFields()
  {
    console.log("Отмена!");
    this.newNews = new NewNews();
  }
}
