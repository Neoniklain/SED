import { Routes, RouterModule } from '@angular/router';
import {ListNewsComponent} from "./components/list-news/list-news.component";
import {EditorListNewsComponent} from "./components/editor-list-news/editor-list-news.component";
import {EditorSingleNewsComponent} from "./components/editor-single-news/editor-single-news.component";

const routes: Routes = [
  { path: '', component: ListNewsComponent }, //Путь по умолчанию
  { path: 'news', component: ListNewsComponent },
  { path: 'list', component: EditorListNewsComponent },
  { path: 'editor', component: EditorSingleNewsComponent },
  { path: 'editor/:id', component: EditorSingleNewsComponent }
];
export const NewsRoutes = RouterModule.forChild(routes);
