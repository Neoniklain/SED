import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '**', redirectTo: './news/news.module#NewsModule', pathMatch: 'full' },
  { path: 'test', loadChildren: './test/test.module#TestModule' },
  { path: 'news', loadChildren: './news/news.module#NewsModule' },
  { path: 'account', loadChildren: './account/account.module#AccountModule' }
];



export const AppRoutes = RouterModule.forRoot(routes);

