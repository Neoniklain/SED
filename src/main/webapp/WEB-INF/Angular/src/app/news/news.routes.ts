import { Routes, RouterModule } from '@angular/router';
import {ListNewsComponent} from "./components/list-news/list-news.component";
import {EditorComponent} from "./components/editor/editor.component";

const routes: Routes = [
  { path: '', component: ListNewsComponent }, //Путь по умолчанию
  { path: 'news', component: ListNewsComponent },
  { path: 'editor', component: EditorComponent }
];
export const NewsRoutes = RouterModule.forChild(routes);
