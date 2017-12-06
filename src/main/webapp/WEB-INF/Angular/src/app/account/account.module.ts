//Core (Ядро)
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//Компоненты
import {AccountComponent} from "./components/account/account.component";
import {LogInComponent} from "./components/authentification/login/login.component";
import {RegisterComponent} from "./components/authentification/register/register.component";
import { DocumentComponent } from "./components/account/documents/document.component";
import {SingleNewsComponent} from "../export/component/news/single-news.component";

//Роутинг
import {AccountRoutes} from "./account.routes";
@NgModule({
  declarations: [
    AccountComponent,
    LogInComponent,
    RegisterComponent,
    DocumentComponent
  ],
  imports: [   //Сюда импорты (типа файлы, которые нужно добавить в код)
    AccountRoutes,
    CommonModule,
    FormsModule,
    HttpModule
  ],
  exports: [
    SingleNewsComponent
    //    OrderListComponent, OrderDetailsComponent, OrderNewComponent
  ],
  providers: [] //Сюда сервисы
})
export class AccountModule { }
