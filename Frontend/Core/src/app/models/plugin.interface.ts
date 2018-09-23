export interface PluginInformation {
    system: {
        baseURL: string
        paths: {
            npm: string
        }
    };
    header: {
        components: string[]
    };
    plugins: Array<{
        id: string,
        name: string,
        mainClass: string
    }>;
    routes: Array<{
        name: string,
        path: string,
        component: {
            module: string,
            componentType: string
        }
    }>;
}
