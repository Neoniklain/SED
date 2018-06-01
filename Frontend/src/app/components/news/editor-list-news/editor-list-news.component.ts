import { ConfirmationService, Message } from "primeng/primeng";
import { MessageService } from "primeng/components/common/messageservice";
import { Component } from '@angular/core';
import { News } from "../../../models/news/news.model";
import { NewsService } from "../../../services/news.service";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {Router} from "@angular/router";
import {NotificationService} from "../../../services/notification.service";
import {StatusType} from "../../../models/statusType.model";

@Component({
  selector: 'editor-list-news-page',
  templateUrl: './editor-list-news.component.html',
  styleUrls: ['./editor-list-news.component.css']
})

export class EditorListNewsComponent  {

  public _listOfNews: News[];
  public msgs: Message[] = [];

  constructor(
    private router: Router,
    private newsService: NewsService,
    private notification: NotificationService,
    private confirmationService: ConfirmationService) {
    this._listOfNews = [];
    this.GetNews();
  }

  public GetNews() {
    this.newsService.GetAll().subscribe(
    (res) => {
      this._listOfNews = res.data;
      // обрезаем большие строки
      for (let item of this._listOfNews) {
        if (item.content.length > 200)
          item.content = item.content.slice(0, 300) + "...";
      }
    }
    );
  }

  public Delete(id: number) {
    this.confirmationService.confirm({
      message: 'Вы действительно хотите удалить эту статью?',
      header: 'Удалить статью?',
      icon: 'fa fa-trash',
      accept: () => {
        this.newsService.Delete(id).subscribe(
          (result) => {
            if (result.status === StatusType.OK.toString()) {
                for (let item of this._listOfNews) {
                    if (item.id == id) {
                        this._listOfNews.splice(this._listOfNews.indexOf(item), 1);
                    }
                }
            }
            this.notification.FromStatus(result);
          });
      }
    });

  }

    public gotoEdit(newsId: number) {
        this.router.navigateByUrl(RouteConstants.News.Edit.replace(":id", newsId.toString()));
    }
}
