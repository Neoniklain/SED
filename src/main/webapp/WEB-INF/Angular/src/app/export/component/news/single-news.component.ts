import {Component, Input, OnInit} from '@angular/core';
import {News} from "../../../models/news.model";

@Component({
  selector: 'single-news',
  templateUrl: './single-news.component.html'
})

export class SingleNewsComponent implements OnInit{

  private show:boolean = false;
  @Input() item: News;

  ngOnInit(): void {

  }

  public Show() {
    this.show = true;
  }
  public Hide() {
    this.show = false;
  }
  public toogle() {
    if(this.show==false)
      this.show=true;
    else
      this.show=false;
  }

}
