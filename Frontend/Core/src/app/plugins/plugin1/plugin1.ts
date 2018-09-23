import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-page1',
    templateUrl: './plugin1.html',
    styleUrls: ['./plugin1.css']
})
export class Page1Component implements OnInit {

    constructor() { }

     ngOnInit() { }

     public getPluginName() {
        return 'Первый плагин';
     }
}
