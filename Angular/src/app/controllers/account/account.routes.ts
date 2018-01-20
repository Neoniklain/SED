import { Routes, RouterModule } from '@angular/router';
import {AccountComponent} from "./components/account/account.component";
import {RegisterComponent} from "./components/authentification/register/register.component";
import {LogInComponent} from "./components/authentification/login/login.component";

const routes: Routes = [
  { path: '', component: LogInComponent }, //Путь по умолчанию
  { path: 'login', component: LogInComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'account', component: AccountComponent},
];
export const AccountRoutes = RouterModule.forChild(routes);
