import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';

// services ----------------
import { TestService} from "./services/test.service";

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
        TestService
      ]
    };
  }
}
