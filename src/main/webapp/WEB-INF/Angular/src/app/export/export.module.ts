//Core (Ядро)
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//Компоненты
//Роутинг
import {ExportRoutes} from "./export.routes";
import {SingleNewsComponent} from "./component/news/single-news.component";

@NgModule({
  declarations: [
    SingleNewsComponent
  ],
  imports: [
    ExportRoutes,
    HttpModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [
    SingleNewsComponent
  ],
})
export class ExportModule { }
