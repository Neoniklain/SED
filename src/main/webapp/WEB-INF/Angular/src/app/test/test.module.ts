//Core (Ядро)
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//Компоненты
import {TestComponent} from "./components/test.component";
//Роутинг
import {TestRoutes} from "./test.routes";

@NgModule({
  declarations: [
    TestComponent
  ],
  imports: [   //Сюда импорты (типа файлы, которые нужно добавить в код)
    TestRoutes,
    CommonModule,
    FormsModule,
    HttpModule
  ],
  exports: [
    //    OrderListComponent, OrderDetailsComponent, OrderNewComponent
  ],
  providers: [] //Сюда сервисы
})
export class TestModule { }
