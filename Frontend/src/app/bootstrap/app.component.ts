import { Component, OnInit } from "@angular/core";
import {Title} from "@angular/platform-browser";
import { Router, NavigationEnd, ActivatedRoute } from "@angular/router";
import { LocaleService, TranslationService } from "angular-l10n";
import { LANG_RU_TRANS } from "../translate/lang-ru";

declare var window: any;

@Component({
    selector: "app-component",
    templateUrl: "./app.component.html",
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

    constructor(public locale: LocaleService,
        public translation: TranslationService,
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private titleService: Title
        ) {
        this.locale.addConfiguration()
            .addLanguage("ru")
            .setCookieExpiration(30)
            .defineLanguage("ru");
        this.locale.init();

        this.translation.addConfiguration()
            .addTranslation("ru", LANG_RU_TRANS);
        this.translation.init();

        if(window.loading_screen) {
            window.loading_screen.finish();
        }
    }

    ngOnInit() {
         this.router
        .events
        .map(() => {
          let child = this.activatedRoute.firstChild;
          while (child) {
            if (child.firstChild) {
              child = child.firstChild;
            } else if (child.snapshot.data && child.snapshot.data['title']) {
              return child.snapshot.data['title'];
            } else {
              return null;
            }
          }
          return null;
        }).subscribe( (title: any) => {
           this.titleService.setTitle(title + " - UNESCO");
       });
    }

}