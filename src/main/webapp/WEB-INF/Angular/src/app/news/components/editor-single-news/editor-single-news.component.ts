import { Component } from '@angular/core';
import { News} from "../../../models/news.model";
import {NewsService} from "../../../core/services/news.service";
import {ActivatedRoute} from "@angular/router";
import {isUndefined} from "util";
import {Location} from '@angular/common';
import {MessageService} from "primeng/components/common/messageservice";
import {Message} from "primeng/components/common/message";

@Component({
  selector: 'editor-single-news-page',
  templateUrl: './editor-single-news.component.html',
  styleUrls: [ './editor-single-news.component.css' ]
})

export class EditorSingleNewsComponent  {
  private typeOfImg:String="";
  private id:number;

  public newNews:News;
  public msgs: Message[] = [];

  constructor(
    private _location: Location,
    private activateRoute: ActivatedRoute,
    private newsService:NewsService,
    private messageService: MessageService)
  {
    this.newNews = new News();
    this.id = activateRoute.snapshot.params['id'];
    if(!isUndefined(this.id))
    {
      this.GetNews(this.id);
    }
  }

  public GetNews(id:number)
  {
    this.newsService.Get(id)
      .subscribe((res: any) => {
          this.newNews = res;
          console.log("Новость получена");
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }
  public SaveNews()
  {
    this.newsService.Save(this.newNews)
      .subscribe((res: any) => {
          this.messageService.add({severity:'success', summary:'Успешно.', detail:'Новость успешно создана.'})
          setTimeout(()=>{ this._location.back() }, 1500)
        },
        (error: any) => {
          console.error('Error: ' + error);
        });
  }
  public onFileChange(evt:any)
  {
    var files = evt.target.files;
    var file = files[0];
    if (files && file) {
      var reader = new FileReader();
      this.typeOfImg = file.type;
      reader.onload =this._handleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
    }
  }
  private _handleReaderLoaded(readerEvt)
  {
    var binaryString = readerEvt.target.result;
    this.newNews.image="data:"+this.typeOfImg+";base64,"+btoa(binaryString);
  }
  public GetBack()
  {
    console.log("Отмена!");
    this._location.back();
  }
}
