import { Component, OnInit } from '@angular/core';
import { Extension } from '../../../plugins-core/src/extension';

@Extension("my-button", [])
@Component({
    selector: "my-button",
    template: `<button>My Button</button>`
})
export class MyButtonComponent implements OnInit {

    ngOnInit() {
        console.log("My Button Init");
    }

}