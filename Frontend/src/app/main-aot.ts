require("jquery");
require("bootstrap-material-design");

import {enableProdMode} from "@angular/core";
import { platformBrowser } from "@angular/platform-browser";
import { AppModuleNgFactory } from "./aot/src/app/bootstrap/app.module.ngfactory";

enableProdMode();

platformBrowser().bootstrapModuleFactory(AppModuleNgFactory);
