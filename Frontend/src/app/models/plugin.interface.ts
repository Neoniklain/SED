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
    plugins: {
        modules: any,
        routes: Array<{
            name: string,
            path: string,
            component: {
                module: string,
                componentType: string
            }
        }>
    };
}
