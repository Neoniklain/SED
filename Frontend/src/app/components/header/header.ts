import { Component, ViewContainerRef, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";

@Component({
    selector: 'header-component',
    templateUrl: "./header.html",
    styleUrls: ["./header.css"],
})

export class HeaderComponent implements OnInit {

    accountService: any;
    private temp:Date = new Date();
    public curYear:any = this.temp.getFullYear();
    public isAuthoried:boolean;
    public role:string;

    constructor(private router: Router) {

    }

    ngOnInit(): void {
    }

    logout(): void {
        console.log("logout");
        localStorage.removeItem("token");
        this.isAuthoried=false;
        this.router.navigate(['/news']);
    }

    ngDoCheck() {
        let token = localStorage.getItem("token");
        token == null ? this.isAuthoried=false : this.isAuthoried=true;
    }

}
