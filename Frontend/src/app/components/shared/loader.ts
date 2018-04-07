import {Component, OnInit, OnChanges} from "@angular/core";
import {Router} from "@angular/router";
import {DictionaryService} from "../../services/dictionary.service";

@Component({
   selector: 'data-loader',
   templateUrl: "./loader.html",
   styleUrls: ["./loader.css"],
})

export class LoaderComponent implements OnInit {

   constructor() {}

   ngOnInit() {
   }

}