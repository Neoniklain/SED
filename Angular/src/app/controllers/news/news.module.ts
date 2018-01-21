//Core (Ядро)
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//Компоненты
import {ListNewsComponent} from "./components/list-news/list-news.component";
//Роутинг
import {NewsRoutes} from "./news.routes";
import {SingleNewsComponent} from "./components/single-news/single-news.component";
import {EditorListNewsComponent} from "./components/editor-list-news/editor-list-news.component";
import {EditorSingleNewsComponent} from "./components/editor-single-news/editor-single-news.component";
// Импорты
import {GrowlModule} from 'primeng/primeng';
import {MessageService} from "primeng/components/common/messageservice";
import {ConfirmDialogModule,ConfirmationService} from 'primeng/primeng';
import {ButtonModule} from "primeng/components/button/button";

//Экспорты

@NgModule({
  declarations: [
    ListNewsComponent,
    EditorSingleNewsComponent,
    SingleNewsComponent,
    EditorListNewsComponent
  ],
  imports: [   //Сюда импорты (типа файлы, которые нужно добавить в код)
    NewsRoutes,
    CommonModule,
    FormsModule,
    HttpModule,
    GrowlModule,
    ConfirmDialogModule,
    ButtonModule
  ],
  exports: [
    SingleNewsComponent,
    ListNewsComponent
    //    OrderListComponent, OrderDetailsComponent, OrderNewComponent
  ],
  providers: [MessageService,ConfirmationService] //Сюда сервисы
})
export class NewsModule { }
