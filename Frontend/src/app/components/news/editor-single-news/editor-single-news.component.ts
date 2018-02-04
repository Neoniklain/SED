import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {isUndefined} from "util";
import {Location} from '@angular/common';
import {MessageService} from "primeng/components/common/messageservice";
import {Message} from "primeng/components/common/message";
import {News} from "../../../models/news.model";
import {NewsService} from "../../../services/news.service";

@Component({
  selector: 'editor-single-news-page',
  templateUrl: './editor-single-news.component.html',
  styleUrls: [ './editor-single-news.component.css' ]
})

export class EditorSingleNewsComponent  {
  private typeOfImg: String= "";
  private id: number;

  public newNews: News;
  public msgs: Message[] = [];

  constructor(
    private _location: Location,
    private activateRoute: ActivatedRoute,
    private newsService: NewsService,
    private messageService: MessageService) {
      this.newNews = new News();
      this.id = activateRoute.snapshot.params['id'];
      if (!isUndefined(this.id)) {
        this.GetNews(this.id);
      }
  }

  public GetNews(id: number) {
    this.newsService.Get(id)
      .subscribe((res: any) => {
          this.newNews = res;
          console.log("Новость получена");
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }

  public SaveNews() {
    this.newsService.Save(this.newNews).subscribe(
          res => {
          this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Новость успешно создана.'});
          setTimeout(() => { this._location.back(); }, 1500);
        },
        error => console.error(error),
      );
  }

  public onFileChange(evt: any) {
    let files = evt.target.files;
    let file = files[0];
    if (files && file) {
      let reader = new FileReader();
      this.typeOfImg = file.type;
      reader.onload = this._handleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
    }
  }

  private _handleReaderLoaded(readerEvt) {
    let binaryString = readerEvt.target.result;
    this.newNews.image = "data:" + this.typeOfImg + ";base64," + btoa(binaryString);
  }

  public GetBack() {
    console.log("Отмена!");
    this._location.back();
  }
}
