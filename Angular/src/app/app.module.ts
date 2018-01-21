import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from "@angular/http";
import { FormsModule } from "@angular/forms";

import { CoreModule } from "./core/core.module";
import { AppComponent } from './app.component';
import { AppRoutes } from "./app.routes";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NotFoundModule} from "./controllers/notfound/notfound.module";
import {NewsModule} from "./controllers/news/news.module";
import {AccountModule} from "./controllers/account/account.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutes,
    CoreModule.forRoot(),
    HttpModule,
    FormsModule,
    BrowserAnimationsModule,
    NotFoundModule,
    NewsModule,
    AccountModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [
  ],
})
export class AppModule { }
