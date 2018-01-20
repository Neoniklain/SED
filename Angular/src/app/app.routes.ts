import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: 'news', loadChildren: './controllers/news/news.module#NewsModule' },
  { path: 'account', loadChildren: './controllers/account/account.module#AccountModule' }
];



export const AppRoutes = RouterModule.forRoot(routes);

