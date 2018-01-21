import { Routes, RouterModule } from '@angular/router';
import {NotFoundComponent} from "./controllers/notfound/component/notfound.component";

const routes: Routes = [
  { path: '',  loadChildren: './controllers/news/news.module#NewsModule' },
  { path: 'news', loadChildren: './controllers/news/news.module#NewsModule' },
  { path: 'account', loadChildren: './controllers/account/account.module#AccountModule' },
  { path: '404',  loadChildren: './controllers/notfound/notfound.module#NotFoundModule' },
  { path: '**',  component: NotFoundComponent }
];



export const AppRoutes = RouterModule.forRoot(routes);

