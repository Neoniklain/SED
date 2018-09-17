import {CommonModule, registerLocaleData} from "@angular/common";
import {LOCALE_ID, NgModule} from "@angular/core";
import {UrlSerializer} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpModule, RequestOptions} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {TranslationModule, TranslationService} from "angular-l10n";
import localeRu from '@angular/common/locales/ru';
// custom
import {routing} from "./app.routes";
import {LowerCaseUrlSerializer} from "../providers/router";
import {EnumKeysPipe} from "../pipes/enum.keys";
import {Globals} from "../globals";
import {ProfileDirective} from "../directive/profile.dirictive";
import {EnumStringKeysPipe} from "../pipes/enum.string.keys";
import {CheckboxModule, EditorModule, RadioButtonModule} from "primeng/primeng";
import {ConfirmDialogModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {DialogModule} from 'primeng/primeng';
import {GrowlModule} from 'primeng/primeng';
import {AutoCompleteModule} from 'primeng/primeng';
import {CalendarModule} from 'primeng/primeng';
import {DataTableModule} from 'primeng/primeng';
import {DragDropModule} from 'primeng/primeng';
import {DropdownModule} from 'primeng/primeng';
import {InputSwitchModule} from 'primeng/primeng';
import {PasswordModule} from 'primeng/primeng';
import {SelectButtonModule} from 'primeng/primeng';
import {ToggleButtonModule} from 'primeng/primeng';
import {ConfirmationService} from 'primeng/primeng';
import {MessageService} from 'primeng/primeng';
// http
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {GlobalHttpOptions} from "../http/globalHttpOptions";
import {ServiceHttpInterceptor} from "../http/serviceHttpInterceptor";
// services
import {NewsService} from "../services/news.service";
import {TaskService} from "../services/task.service";
import {AuthenticationService} from "../services/authService";
import {ScheduleService} from "../services/schedule.service";
import {PluginService} from "../services/plugin.service";
import {AccountService} from "../services/accountService";
import {DictionaryService} from "../services/dictionary.service";
import {JournalService} from "../services/journal.service";
import {UtilsService} from "../services/utils.service";
import {NotificationService} from "../services/notification.service";
import {HandelErrorService} from "../services/handelError.service";
import {FileService} from "../services/file.service";
// guards
import {AuthGuard} from "../guards/auth.guard";
// components
import {AppComponent} from "./app.component";
import {SingleNewsComponent} from "../components/news/single-news/single-news.component";
import {EditorSingleNewsComponent} from "../components/news/editor-single-news/editor-single-news.component";
import {EditorListNewsComponent} from "../components/news/editor-list-news/editor-list-news.component";
import {ListNewsComponent} from "../components/news/list-news/list-news.component";
import {NotFoundComponent} from "../components/notfound/notfound.component";
import {TaskDescListComponent} from "../components/task/taskList/taskDescList.component";
import {PairCreateComponent} from "../components.page/adminPanel/pairCreate/create-pair.component";
import {HeaderComponent} from "../components/shared/header/header";
import {AccessDeniedComponent} from "../components/shared/accessDenied/accessDenied.component";
import {LoaderComponent} from "../components/shared/loader/loader";
import {WeekScheduleComponent} from "../components/schedule/weekSchedule/weekSchedule.component";
import {DepartmentScheduleComponent} from "../components/schedule/departmentSchedule/departmentShedule.component";
import {PairDetailsComponent} from "../components/schedule/pairDetails/pairDeatails.component";
import {NewTaskDescComponent} from "../components/task/newTask/newTaskDesc.component";
import {WorkTaskComponent} from "../components/task/workTask/workTask.component";
import {ShowScheduleComponent} from "../components/schedule/showSchedule/show-schedule.component";
import {JournalComponent} from "../components/journal/journal/journal.component";
import {DictionaryTableAddComponent} from "../components.page/adminPanel/dictionaryTable/added/dictionaryTableAdd.component";
import {ParserXmlComponent} from "../components.page/adminPanel/parserXml/parserXml.component";
import {DictionaryComponent} from "../components.page/adminPanel/dictionaryTable/dictionaryTable.component";
import {AdminPanelComponent} from "../components.page/adminPanel/adminPanel";
import {SettingsPageComponent} from "../components.page/account/settings-page/settings-page.component";
import {LessonConfiguratorPageComponent} from "../components.page/account/lesson-configurator-page/lesson-configurator-page.component";
import {JournalPageComponent} from "../components.page/account/journal-page/journal-page.component";
import {LessonСonfiguratorComponent} from "../components/journal/lesson-configurator/lesson-configurator.component";
import {AccountComponent} from "../components.page/account/account.component";
import {NewsDispatcherComponent} from "../components.page/account/news-dispatcher/news-dispatcher.component";
import {LogInComponent} from "../components.page/login/login.component";
import {UserAddComponent} from "../components.page/adminPanel/userAdd/userAdd";
import {LessonListComponent} from "../components/schedule/lessonsList/lessonsList.component";
import {ProfileComponent} from "../components/shared/profile/profile";
import {LessonDetailsComponent} from "../components/schedule/lesson-details/lessonDetails.component";
import {AccessControlComponent} from "../components.page/adminPanel/accessControl/accessControl.component";
import {DictionaryTableComponent} from "../components/shared/dictionaryTable/dictionaryTable.component";
import {HasAccessRightDirective} from "../directive/hasAccessRight.dirictive";
import {HasRoleDirective} from "../directive/hasRole.dirictive";
import {UserSearchComponent} from "../components/shared/userSearch/userSearch";
import {FileUploadModule} from "ng2-file-upload";
import {ScheduleComponent} from "../components.page/schedule/schedule.component";

registerLocaleData(localeRu);

@NgModule({
    imports: [
        CommonModule,
        BrowserAnimationsModule,
        BrowserModule,
        FormsModule,
        HttpModule,
        HttpClientModule,
        routing,
        TranslationModule.forRoot(),
        DialogModule,
        GrowlModule,
        DataTableModule,
        SelectButtonModule,
        DragDropModule,
        InputSwitchModule,
        CheckboxModule,
        CalendarModule,
        DropdownModule,
        InputTextModule,
        PasswordModule,
        AutoCompleteModule,
        ConfirmDialogModule,
        ToggleButtonModule,
        EditorModule,
        FileUploadModule,
        RadioButtonModule
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
        ProfileDirective,
        DictionaryComponent,
        ParserXmlComponent,
        WeekScheduleComponent,
        DictionaryTableAddComponent,
        LoaderComponent,
        JournalComponent,
        LessonСonfiguratorComponent,
        DictionaryTableComponent,
        AccessControlComponent,
        DepartmentScheduleComponent,
        HasAccessRightDirective,
        HasRoleDirective,
        ShowScheduleComponent,
        PairCreateComponent,
        PairDetailsComponent,
        LessonListComponent,
        LessonDetailsComponent,
        ScheduleComponent,
        UserSearchComponent
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
        ScheduleService,
        PluginService,
        FileService
    ],
    bootstrap: [
        AppComponent
    ]
})

export class AppModule {
}