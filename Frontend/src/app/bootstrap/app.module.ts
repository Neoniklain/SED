import {CommonModule} from "@angular/common";
import {LOCALE_ID, NgModule} from "@angular/core";
import {UrlSerializer} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpModule, RequestOptions} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {TranslationModule, TranslationService} from "angular-l10n";
import {ToastrModule} from "ngx-toastr";
import {Ng2AutoCompleteModule} from "ng2-auto-complete";
import {AutoCompleteModule} from 'primeng/components/autocomplete/autocomplete';
import {FileSelectDirective} from 'ng2-file-upload';
import {FileUploadModule} from 'primeng/components/fileupload/fileupload';
// custom
import {routing} from "./app.routes";
import {LowerCaseUrlSerializer} from "../providers/router";
import {EnumKeysPipe} from "../pipes/enum.keys";
// http
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {GlobalHttpOptions} from "../http/globalHttpOptions";
import {ServiceHttpInterceptor} from "../http/serviceHttpInterceptor";
// components
import {AppComponent} from "./app.component";
import {AccountComponent} from "../components/account/account/account.component";
import {NewsDispatcherComponent} from "../components/account/account/news-dispatcher/news-dispatcher.component";
import {LogInComponent} from "../components/account/authentification/login/login.component";
import {SingleNewsComponent} from "../components/news/single-news/single-news.component";
import {EditorSingleNewsComponent} from "../components/news/editor-single-news/editor-single-news.component";
import {EditorListNewsComponent} from "../components/news/editor-list-news/editor-list-news.component";
import {ListNewsComponent} from "../components/news/list-news/list-news.component";
import {NotFoundComponent} from "../components/notfound/notfound.component";
import {TaskDescListComponent} from "../components/task/taskList/taskDescList.component";
import {UserAddComponent} from "../components/adminPanel/userAdd/userAdd";
import {PairCreateComponent} from "../components/schedule/pairCreate/create-pair.component";
// services
import {NewsService} from "../services/news.service";
import {TaskService} from "../services/task.service";
import {AuthenticationService} from "../services/authService";
import {PairService} from "../services/pair.service";
import {PluginService} from "../services/plugin.service";
// guards
import {AuthGuard} from "../guards/auth.guard";
import {HeaderComponent} from "../components/header/header";
import {DialogModule} from "primeng/components/dialog/dialog";
import {GrowlModule} from "primeng/components/growl/growl";
import {ConfirmDialogModule} from "primeng/components/confirmdialog/confirmdialog";
import {MessageService} from "primeng/components/common/messageservice";
import {
    CalendarModule,
    ConfirmationService,
    DataTableModule,
    DropdownModule,
    MultiSelectModule,
    PasswordModule, ToggleButtonModule
} from "primeng/primeng";
import {AccountService} from "../services/accountService";
import {AccessDeniedComponent} from "../components/account/accessDenied/accessDenied.component";
import {AdminPanelComponent} from "../components/adminPanel/adminPanel";
import {HasRoleDirective} from "../directive/hasRole.dirictive";
import {Globals} from "../globals";
import {DictionaryTableComponent} from "../components/adminPanel/dictionaryTable/dictionaryTable.component";
import {DictionaryService} from "../services/dictionary.service";
import {ParserXmlComponent} from "../components/adminPanel/parserXml/parserXml.component";
import {JournalComponent} from "../components/journal/journal.component";
import {JournalService} from "../services/journal.service";
import {DictionaryTableAddComponent} from "../components/adminPanel/dictionaryTable/added/dictionaryTableAdd.component";
import {LoaderComponent} from "../components/shared/loader/loader";
import {UtilsService} from "../services/utils.service";
import {WeekScheduleComponent} from "../components/schedule/weekSchedule/weekSchedule.component";
import {ShowScheduleComponent} from "../components/adminPanel/showSchedule/show-schedule.component";
import {DepartmentScheduleComponent} from "../components/schedule/departmentSchedule/departmentShedule.component";
import {PairDetailsComponent} from "../components/schedule/pairDetails/pairDeatails.component";
import {NewTaskDescComponent} from "../components/task/newTask/newTaskDesc.component";
import {WorkTaskComponent} from "../components/task/workTask/workTask.component";
import {EnumStringKeysPipe} from "../pipes/enum.string.keys";
import {NotificationService} from "../services/notification.service";
import {HandelErrorService} from "../services/handelError.service";
import {JournalPageComponent} from "../components/account/account/journal-page/journal-page.component";
import {LessonConfiguratorPageComponent} from "../components/account/account/lesson-configurator-page/lesson-configurator-page.component";
import {LessonСonfiguratorComponent} from "../components/journal/lesson-configurator/lesson-configurator.component";
import {InputTextModule} from "primeng/components/inputtext/inputtext";
import {SettingsPageComponent} from "../components/account/account/settings-page/settings-page.component";
import {ProfileComponent} from "../components/shared/profile/profile";
import {ProfileDirective} from "../directive/profile.dirictive";
import {FileService} from "../services/file.service";

@NgModule({
   imports: [
      CommonModule,
      BrowserAnimationsModule,
      ToastrModule.forRoot({preventDuplicates: true}),
      BrowserModule,
      FormsModule,
      HttpModule,
      HttpClientModule,
      Ng2AutoCompleteModule,
      routing,
      TranslationModule.forRoot(),
      DialogModule,
      GrowlModule,
      DataTableModule,
      CalendarModule,
      DropdownModule,
      InputTextModule,
      PasswordModule,
      AutoCompleteModule,
      ConfirmDialogModule,
      ToggleButtonModule,
      FileUploadModule
   ],
    entryComponents: [
        ProfileComponent
    ],
   declarations: [
      AppComponent,
      AccountComponent,
      NewsDispatcherComponent,
      TaskDescListComponent,
      NewTaskDescComponent,
      WorkTaskComponent,
      ProfileComponent,
      LogInComponent,
      JournalPageComponent,
      LessonConfiguratorPageComponent,
      SingleNewsComponent,
      ListNewsComponent,
      SettingsPageComponent,
      UserAddComponent,
      HeaderComponent,
      EditorSingleNewsComponent,
      EditorListNewsComponent,
      AdminPanelComponent,
      NotFoundComponent,
      AccessDeniedComponent,
      EnumKeysPipe,
      EnumStringKeysPipe,
      FileSelectDirective,
      ProfileDirective,
      DictionaryTableComponent,
      ParserXmlComponent,
      WeekScheduleComponent,
      DictionaryTableAddComponent,
      LoaderComponent,
      JournalComponent,
      LessonСonfiguratorComponent,
      DepartmentScheduleComponent,
      HasRoleDirective,
      ShowScheduleComponent,
      PairCreateComponent,
      PairDetailsComponent
   ],
   providers: [
      {provide: RequestOptions, useClass: GlobalHttpOptions},
      {provide: UrlSerializer, useClass: LowerCaseUrlSerializer},
      {provide: HTTP_INTERCEPTORS, useClass: ServiceHttpInterceptor, multi: true},
      { provide: LOCALE_ID, useValue: 'ru-RU' },
      TranslationService,
      AuthenticationService,
      NotificationService,
      HandelErrorService,
      TaskService,
      NewsService,
      MessageService,
      AccountService,
      UtilsService,
      DictionaryService,
      ConfirmationService,
      JournalService,
      AuthGuard,
      Globals,
      PairService,
      PluginService,
      FileService
   ],
   bootstrap: [
      AppComponent
   ]
})

export class AppModule {
}