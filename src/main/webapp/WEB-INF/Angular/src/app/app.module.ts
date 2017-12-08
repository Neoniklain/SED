import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from "@angular/http";
import { FormsModule } from "@angular/forms";

import { CoreModule } from "./core/core.module";
import { AppComponent } from './app.component';
import { AppRoutes } from "./app.routes";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutes,
    CoreModule.forRoot(),
    HttpModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [
  ],
})
export class AppModule { }
