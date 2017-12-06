import { Routes, RouterModule } from '@angular/router';
import {SingleNewsComponent} from "./component/news/single-news.component";

const routes: Routes = [
  { path: '', component: SingleNewsComponent } //Путь по умолчанию
];



export const ExportRoutes = RouterModule.forRoot(routes);

