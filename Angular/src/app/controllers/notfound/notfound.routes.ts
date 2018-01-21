import { Routes, RouterModule } from '@angular/router';
import {NotFoundComponent} from "./component/notfound.component";

const routes: Routes = [
  { path: '404', component: NotFoundComponent }, //Путь по умолчанию
];

export const NotFoundRoutes = RouterModule.forChild(routes);

