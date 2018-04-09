import {Component, OnInit} from "@angular/core";
import {Professor} from "../../models/professor.model";
import {ProfessorService} from "../../services/professor.service";
import {RouteConstants} from "../../bootstrap/app.route.constants";
import {Group} from "../../models/group.model";
import {GroupService} from "../../services/group.service";
import {Router} from "@angular/router";

@Component({
    selector: 'list-professors-page',
    templateUrl: './list-professors.component.html'
})

export class ListProfessorsComponent implements OnInit {

    public profs: Professor[];
    public groups: Group[];
    public RouteConstants;

    constructor(private profsService: ProfessorService, private groupsService: GroupService) {
        this.profs = [];
        this.GetProfs();

        this.groups = [];
        this.GetGroups();
    }

    ngOnInit() {
        this.RouteConstants = RouteConstants;
    }

    public GetProfs() {
        this.profsService.GetAll()
            .subscribe((res: any) => {
                    this.profs = res;
                },
                error => console.error(error));
    }

    public GetGroups() {
        this.groupsService.GetAll()
            .subscribe((res: any) => {
                    this.groups = res;
                },
                error => console.error(error));
    }

}

