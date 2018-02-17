import { Component, ViewContainerRef, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";
import {Message} from "primeng/components/common/message";

@Component({
   selector: 'admin-panel-page',
   templateUrl: "./adminPanel.html",
    styleUrls: ["./adminPanel.css"],
})

export class AdminPanelComponent implements OnInit {

    constructor(private router: Router) {

    }

    ngOnInit(): void {
    }

}
