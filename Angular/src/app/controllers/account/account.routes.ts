import { Routes, RouterModule } from '@angular/router';
import {AccountComponent} from "./components/account/account.component";
import {RegisterComponent} from "./components/authentification/register/register.component";
import {LogInComponent} from "./components/authentification/login/login.component";

const routes: Routes = [
  { path: '', component: AccountComponent }, //Путь по умолчанию
  { path: 'account', component: AccountComponent }, //Путь по умолчанию
  { path: 'login', component: LogInComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'lk', component: AccountComponent},
];
export const AccountRoutes = RouterModule.forChild(routes);
