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

declare var SystemJS;
declare var window: any;

@Component({
    selector: "app-component",
    templateUrl: "./app.component.html",
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit, AfterViewInit {


    @ViewChild('content', { read: ViewContainerRef })
    content: ViewContainerRef;

    private module;

    constructor(public locale: LocaleService,
        public translation: TranslationService,
        private compiler: Compiler,
        private router: Router,
        private http: HttpClient,
        private injector: Injector,
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

    async ngAfterViewInit() {
        // Получаем файл настроек плагинации
        const url = '/assets/plugins.config.json';
        const config = <PluginInformation> await this.http.get(url).toPromise();
        console.log(config);

        // Устанавливаем конфигурации для SystemJS
        SystemJS.config(config.system);

        await SystemJS.import('plugins-core');
        let pluginExample = await SystemJS.import('plugins-example');

        let core = await SystemJS.import('plugins-core');
        core.pluginManager.register('my-label', pluginExample.MyButtonComponent.prototype.constructor, []);
        const componentType = core.pluginManager.getType('my-label');
        console.log(pluginExample);
        console.log(core);

        const RuntimeModule = NgModule({ declarations: [componentType]})(class {});

        const module = this.compiler.compileModuleAndAllComponentsSync(RuntimeModule);
        const factory = module.componentFactories.find(f => f.componentType === componentType);
        const pluginInjector = Injector.create([
            ...core.pluginManager.getProviders()
        ], this.injector);
        this.content.clear();
        this.content.createComponent(factory, 0, pluginInjector);


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
            } else {
              return null;
            }
          }
          return null;
        }).subscribe( (title: any) => {
           this.titleService.setTitle(title + " - UNESCO");
       });
    }

}