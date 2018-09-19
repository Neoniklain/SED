System.register(['@angular/core'], function (exports, module) {
'use strict';
var Component;
return {
setters: [function (module) {
Component = module.Component;
}],
execute: function () {

/*! *****************************************************************************
Copyright (c) Microsoft Corporation. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at http://www.apache.org/licenses/LICENSE-2.0

THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE,
MERCHANTABLITY OR NON-INFRINGEMENT.

See the Apache Version 2.0 License for specific language governing permissions
and limitations under the License.
***************************************************************************** */
/* global Reflect, Promise */







function __decorate(decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
}

function Extension(name, deps) {
    return (constructor) => {
        Extension.prototype.registry[name] = {
            ctor: constructor,
            deps: deps || []
        };
    };
}

Extension.prototype.registry = {};

Extension.prototype.getProviders = function () {
    var registry = this.registry;
    return Object.keys(registry).map(function (key) {
        return {
            provide: key,
            useClass: registry[key].ctor,
            deps: registry[key].deps
        };
    });
};

Extension.prototype.register = function (name, ctor, deps) {
    this.registry[name] = {
        ctor: ctor,
        deps: deps || []
    };
};

Extension.prototype.exist = function (name) {
    console.log("this.registry", this.registry);
    this.registry[name];
};


Extension.prototype.getExtensionType = function (name) {
    return this.registry[name].ctor;
};

var MyButtonComponent = (exports('MyButtonComponent', function () {
    function MyButtonComponent() {
    }
    MyButtonComponent.prototype.ngOnInit = function () {
        console.log("My Button Init");
    };
    MyButtonComponent = __decorate([
        Extension("my-button", []),
        Component({
            selector: "my-button",
            template: "<button>My Button</button>"
        })
    ], MyButtonComponent);
    return MyButtonComponent;
}()));

}
};
});
