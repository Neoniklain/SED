import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  },
  { path: 'test', loadChildren: './test/test.module#TestModule' }
];
export const AppRoutes = RouterModule.forRoot(routes);
