import {Component, Input, OnInit} from '@angular/core';
import {News} from "../../../../models/news.model";

@Component({
  selector: 'single-news',
  templateUrl: './single-news.component.html',
  styleUrls: ['./single-news.component.css']
})

export class SingleNewsComponent implements OnInit{

  public tags:string[];

  private show:boolean = false;
  @Input() item: News;

  ngOnInit(): void {
    if(this.item.tags!="")
      this.tags = this.item.tags.split(' ');
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
