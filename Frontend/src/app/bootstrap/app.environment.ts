enum Enviromnent {
    Development,
    Test,
    Production
}

export class AppSettings {
    public BaseApiUrl: string;
}

export let appSettings = {
    BaseApiUrl: 'http://http://46.181.179.131:8080/api/',
}

let environment = Enviromnent.Development;
/// #if ENVIRONMENT == "dev"
    environment = Enviromnent.Test;
    appSettings.BaseApiUrl = 'http://46.181.179.131:8080/api/';
/// #endif
/// #if ENVIRONMENT == "test"
    environment = Enviromnent.Test;
    appSettings.BaseApiUrl = 'http://46.181.179.131:8080/api/';
/// #endif
/// #if ENVIRONMENT == "prod"
    environment = Enviromnent.Production;
    appSettings.BaseApiUrl = 'http://46.181.179.131:8080/api/';
/// #endif