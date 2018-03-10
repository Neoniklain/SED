import {Component, OnInit, OnChanges} from "@angular/core";
import {Router} from "@angular/router";
import {User} from "../../models/account/user.model";
import {Discipline} from "../../models/discipline.model";
import {LazyLoadEvent} from "primeng/api";
import {PageResult} from "../../models/admin/PageResult.model.list";
import {DictionaryService} from "../../services/dictionary.service";
import {Dictionary} from "../../models/admin/dictionary.model";

@Component({
   selector: 'data-loader',
   templateUrl: "./loader.html",
   styleUrls: ["./loader.css"],
})

export class LoaderComponent implements OnInit {

   constructor(private router: Router,
               private dictionaryService: DictionaryService) {}

   ngOnInit() {
   }

}