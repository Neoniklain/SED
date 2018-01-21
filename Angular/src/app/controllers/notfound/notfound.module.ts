//Core (Ядро)
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
//Компоненты
//Роутинг
import {NotFoundRoutes} from "./notfound.routes";
import {NotFoundComponent} from "./component/notfound.component";

@NgModule({
  declarations: [
    NotFoundComponent
  ],
  imports: [
    NotFoundRoutes,
    HttpModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [],
  exports: [
    NotFoundComponent
  ],
})
export class NotFoundModule { }
