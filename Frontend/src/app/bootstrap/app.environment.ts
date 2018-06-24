enum Enviromnent {
    Development,
    Test,
    Production
}

export class AppSettings {
    public BaseApiUrl: string;
}

export let appSettings = {
    // BaseApiUrl: 'http://62.173.145.143:8080/api/',
    BaseApiUrl: 'http://localhost:8080/api/',
}

let environment = Enviromnent.Development;
/// #if ENVIRONMENT == "dev"
    environment = Enviromnent.Development;
    // appSettings.BaseApiUrl = 'http://62.173.145.143:8080/api/';
    appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif