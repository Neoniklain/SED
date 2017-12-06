import {Component, Input} from '@angular/core';

@Component({
  selector: 'document-page',
  templateUrl: './document.component.html'
})

export class DocumentComponent{

  private show:boolean = true;
  constructor() {
    this.show = true;
  }

  @Input()
  set setShow(Show:boolean) {
      this.show = Show;
  }
  get getShow():boolean { return this.show; }
}
