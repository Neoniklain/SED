import { Component } from '@angular/core';
import {NewNews} from "../../../models/news.model";
import {NewsService} from "../../../core/services/news.service";

@Component({
  selector: 'editor-page',
  templateUrl: './editor.component.html'
})

export class EditorComponent  {
  public newNews:NewNews;
  private typeOfImg:String="";
  constructor(
    private newsService:NewsService)
  {
    this.newNews = new NewNews();
    this.newNews.image="";
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

  public ClearFields()
  {
    console.log("Отмена!");
    this.newNews = new NewNews();
  }
}
