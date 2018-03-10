"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Enviromnent;
(function (Enviromnent) {
    Enviromnent[Enviromnent["Development"] = 0] = "Development";
    Enviromnent[Enviromnent["Test"] = 1] = "Test";
    Enviromnent[Enviromnent["Production"] = 2] = "Production";
})(Enviromnent || (Enviromnent = {}));
var AppSettings = (function () {
    function AppSettings() {
    }
    return AppSettings;
}());
exports.AppSettings = AppSettings;
exports.appSettings = {
    BaseApiUrl: 'http://localhost:8080/api/',
};
var environment = Enviromnent.Development;
/// #if ENVIRONMENT == "dev"
environment = Enviromnent.Test;
exports.appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif
/// #if ENVIRONMENT == "test"
environment = Enviromnent.Test;
exports.appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif
/// #if ENVIRONMENT == "prod"
environment = Enviromnent.Production;
exports.appSettings.BaseApiUrl = 'http://localhost:8080/api/';
/// #endif 
