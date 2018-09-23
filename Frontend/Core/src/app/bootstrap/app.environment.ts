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

appSettings.BaseApiUrl = process.env.API_URL;
