import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {isUndefined} from "util";
import {Location} from '@angular/common';
import {Message} from "primeng/components/common/message";
import {News} from "../../../models/news/news.model";
import {NewsService} from "../../../services/news.service";
import {AuthenticationService} from "../../../services/authService";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {NotificationService} from "../../../services/notification.service";
import {StatusType} from "../../../models/statusType.model";

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
    private router: Router,
    private authenticationService: AuthenticationService,
    private notification: NotificationService) {
      this.newNews = new News();
      this.authenticationService.getUser().subscribe((res) => {
              this.newNews.author = res.data;
          });
      this.id = activateRoute.snapshot.params['id'];
      if (!isUndefined(this.id)) {
        this.GetNews(this.id);
      }
  }

  public GetNews(id: number) {
    this.newsService.Get(id)
      .subscribe((res) => {
          this.newNews = res.data;
        });
  }

  public SaveNews() {
    this.newsService.Save(this.newNews).subscribe(
        result => {
            if (result.status === StatusType.OK.toString()) {
                setTimeout(() => { this.router.navigate([RouteConstants.News.EditList]); }, 1500);
            }
            this.notification.FromStatus(result);
        }
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
      setTimeout(() => { this.router.navigate([RouteConstants.Account]); }, 1500);
  }
}
