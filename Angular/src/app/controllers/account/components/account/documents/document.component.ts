import {Component, Input} from '@angular/core';

@Component({
  selector: 'document-page',
  templateUrl: './document.component.html'
})

export class DocumentComponent{

  private show:boolean = false;

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
