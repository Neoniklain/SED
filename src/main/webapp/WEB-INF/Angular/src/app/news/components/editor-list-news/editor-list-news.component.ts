import {ConfirmationService, Message} from "primeng/primeng";
import {MessageService} from "primeng/components/common/messageservice";
import { Component } from '@angular/core';
import {News} from "../../../models/news.model";
import {NewsService} from "../../../core/services/news.service";

@Component({
  selector: 'editor-list-news-page',
  templateUrl: './editor-list-news.component.html',
  styleUrls: ['./editor-list-news.component.css']
})

export class EditorListNewsComponent  {

  public _listOfNews:News[];
  public msgs: Message[] = [];

  constructor(
    private newsService:NewsService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) {
    this._listOfNews = [];
    this.GetNews();
  }

  public GetNews()
  {
    this.newsService.GetAll()
      .subscribe(
        (res: any) => {
          this._listOfNews = res;
          // обрезаем большие строки
          for (let item of this._listOfNews) {
            if(item.content.length>200)
              item.content = item.content.slice(0,300) + "...";
          }
          console.log("Данные получены");
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }

  public Delete(id:number){
    this.confirmationService.confirm({
      message: 'Вы действительно хотите удалить эту статью?',
      header: 'Удалить статью?',
      icon: 'fa fa-trash',
      accept: () => {
        this.newsService.Delete(id).subscribe(
          (res: any) => {
            for (let item of this._listOfNews) {
              if(item.id==id)
              {
                this.messageService.add({severity:'success', summary:'Успешно.', detail:'Новость успешно удалена.'})
                this._listOfNews.splice(this._listOfNews.indexOf(item),1)
              }
            }
          },
          (error: any) => { console.error('Error: ' + error); });
      }
    });

  }
}
