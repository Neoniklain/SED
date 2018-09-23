import {Component, OnInit, OnChanges, Input} from "@angular/core";

@Component({
   selector: 'data-loader',
   templateUrl: "./loader.html",
   styleUrls: ["./loader.css"],
})

export class LoaderComponent implements OnInit {

   @Input() show = false;
   @Input() margin: number = 0;

   constructor() {}

   ngOnInit() {
   }

}