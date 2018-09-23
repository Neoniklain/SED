import {
    AfterViewInit,
    Compiler,
    Component,
    Injector,
    NgModule,
    OnInit,
    ViewChild,
    ViewContainerRef
} from "@angular/core";
import {Title} from "@angular/platform-browser";
import { Router, ActivatedRoute } from "@angular/router";
import { LocaleService, TranslationService } from "angular-l10n";
import { LANG_RU_TRANS } from "../translate/lang-ru";
import {PluginInformation} from "../models/plugin.interface";
import {HttpClient} from "@angular/common/http";
import {PluginsModule} from "./plugins.module";
import {Globals} from "../globals";
import {PluginService} from "../services/plugin.service";

declare var SystemJS;
declare var window: any;

@Component({
    selector: "app-component",
    templateUrl: "./app.component.html",
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {


    @ViewChild('content', { read: ViewContainerRef })
    content: ViewContainerRef;

    private module;

    constructor(public locale: LocaleService,
        public translation: TranslationService,
        private compiler: Compiler,
        private router: Router,
        private http: HttpClient,
        private injector: Injector,
        private globals: Globals,
        private pluginService: PluginService,
        private activatedRoute: ActivatedRoute,
        private titleService: Title
        ) {
        this.locale.addConfiguration()
            .addLanguage("ru")
            .setCookieExpiration(30)
            .defineLanguage("ru");
        this.locale.init();

        this.translation.addConfiguration()
            .addTranslation("ru", LANG_RU_TRANS);
        this.translation.init();

        if (window.loading_screen) {
            window.loading_screen.finish();
        }
        this.module = this.compiler.compileModuleAndAllComponentsSync(PluginsModule);
    }

    ngOnInit() {

        this.router
        .events
        .map(() => {
            let child = this.activatedRoute.firstChild;
            while (child) {
                if (child.firstChild) {
                    child = child.firstChild;
                } else if (child.snapshot.data && child.snapshot.data['title']) {
                    return child.snapshot.data['title'];
                }
                else {
                    return null;
                }
            }
            return null;
            }).subscribe( (title: any) => { this.titleService.setTitle(title + " - UNESCO"); });

        this.pluginService.GetPlugin('plugins-example', this.content);
        this.loadPlugin();
    }

    async loadPlugin() {
        // Загрузка файла конфигурации для плагинации
        const url = '/assets/plugins.config.json';
        let config: PluginInformation = await this.http.get<PluginInformation>(url).toPromise();
        // Устанавливаем конфигурацию для библиотеки SystemJS
        SystemJS.config(config.system);
        // Загрузка ядра плагинов
        let core = await SystemJS.import('plugins-core');
        console.log("plugins-core was load ", core);
        let globals = this.globals;
        globals.pluginCore = core;
        console.time("Загрузка всех плагинов");
        // Загрузка плагинов
        for (let plugin of config.plugins) {
            console.time("Загрузка плагина " + plugin.id);
            let load = await SystemJS.import(plugin.id);
            console.log(plugin.id + " was load ", load);
            core.pluginManager.register(plugin.id, load[plugin.mainClass].prototype.constructor, []);
            this.globals.pluginCore = core;
            console.timeEnd("Загрузка плагина " + plugin.id);
        }
        console.timeEnd("Загрузка всех плагинов");
    }

}