export class Plugin {
    public id: string;
    public name: string;
    public mainClass: string;

    constructor() {
        this.id = "";
        this.name = "";
        this.mainClass = "";
    }

}

export interface PluginCore {
    pluginManager: PluginManager;
}

interface PluginManager {
    getType(name: string);
    exist(name: string);
    getProviders();
    register(name: string, ctor, deps: string[]);
}