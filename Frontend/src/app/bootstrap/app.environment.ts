enum Enviromnent {
    Development,
    Test,
    Production
}

export class AppSettings {
    public BaseApiUrl: string;
}

export let appSettings = {
    BaseApiUrl: '',
};

// Prodaction
// appSettings.BaseApiUrl = 'http://192.168.253.8:8080/api/';
// local
appSettings.BaseApiUrl = 'http://localhost:8080/api/';
