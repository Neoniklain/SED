enum Enviromnent {
    Development,
    Test,
    Production
}

export class AppSettings {
    public BaseApiUrl: string;
}

export let appSettings = {
    BaseApiUrl: "",
}

let environment = Enviromnent.Development;
/// #if ENVIRONMENT == "dev"
    environment = Enviromnent.Test;
    appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif
/// #if ENVIRONMENT == "test"
    environment = Enviromnent.Test;
    appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif
/// #if ENVIRONMENT == "prod"
    environment = Enviromnent.Production;
/// #endif