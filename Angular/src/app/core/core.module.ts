import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';

// services ----------------
import { TestService} from "./services/test.service";
import {AccountService} from './services/account.service';
import {NewsService} from "./services/news.service";

@NgModule({
  imports: [
    CommonModule
  ]
})

export class CoreModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [
        TestService,
        AccountService,
        NewsService
      ]
    };
  }
}
