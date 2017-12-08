//Core (Ядро)
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//Компоненты
//Роутинг
import {SharedRoutes} from "./shared.routes";

@NgModule({
  declarations: [
  ],
  imports: [
    SharedRoutes,
    HttpModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [
  ],
})
export class SharedModule { }
