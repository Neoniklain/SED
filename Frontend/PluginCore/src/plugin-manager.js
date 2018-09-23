import { Extension } from './extension';

export class PluginManager {

    getType(name) {
        return Extension.prototype.getExtensionType(name);
    }

    getProviders() {
        return Extension.prototype.getProviders();
    }

    register(name, ctor, deps) {
        return Extension.prototype.register(name, ctor, deps);
    }

    exist(name) {
        return Extension.prototype.exist(name);
    }
}