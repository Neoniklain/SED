enum Enviromnent {
    Development,
    Test,
    Production
}

export class AppSettings {
    public BaseApiUrl: string;
}

export let appSettings = {
    BaseApiUrl: 'http://localhost:8080/api/',
}

let environment = Enviromnent.Development;
/// #if ENVIRONMENT == "dev"
    environment = Enviromnent.Development;
    appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif
/// #if ENVIRONMENT == "test"
    environment = Enviromnent.Test;
    appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif
/// #if ENVIRONMENT == "prod"
    environment = Enviromnent.Production;
    appSettings.BaseApiUrl = 'http://62.173.145.143:8080/api/';
/// #endif