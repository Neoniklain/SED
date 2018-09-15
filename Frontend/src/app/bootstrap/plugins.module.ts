import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Page1Component} from "../plugins/plugin1/plugin1";

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [Page1Component]
})

export class PluginsModule { }