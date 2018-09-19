import {Plugin} from "../models/plugin.model";
import {Compiler, Injectable, Injector, NgModule, ViewContainerRef} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiRouteConstants} from "../bootstrap/app.route.constants";
import {HandelErrorService} from "./handelError.service";
import {catchError, map} from "rxjs/operators";
import {PluginInformation} from "../models/plugin.interface";
import {Globals} from "../globals";


@Injectable()
export class PluginService {

    constructor(private http: HttpClient,
                private globals: Globals,
                private compiler: Compiler,
                private injector: Injector,
                private handleError: HandelErrorService) {
    }

    public GetAll(): Observable<Array<Plugin>> {
        return this.http.get(ApiRouteConstants.Plugin.All)
            .pipe(
                map((res: Array<Plugin>) => res),
                catchError(e => this.handleError.handle(e))
            );
    }

    public GetPluginList(): Observable<Array<Plugin>> {

        const url = '/assets/plugins.config.json';
        return this.http.get<PluginInformation>(url).map(
            res => {
                return res.plugins;
            }
        );
    }

    public GetPlugin(plugin: string, content) {
        return this.globals.getPluginCore.subscribe(core => {
            if (core.pluginManager != null && core.pluginManager.exist(plugin)) {
                const componentType = core.pluginManager.getType(plugin);

                const RuntimeModule = NgModule({ declarations: [componentType]})(class {});

                const module = this.compiler.compileModuleAndAllComponentsSync(RuntimeModule);
                const factory = module.componentFactories.find(f => f.componentType === componentType);
                const pluginInjector = Injector.create([
                    ...core.pluginManager.getProviders()
                ], this.injector);
                content.clear();
                content.createComponent(factory, 0, pluginInjector);
                return content;
            }
        });
    }


}