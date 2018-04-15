import {Component, OnInit} from "@angular/core";
import {Professor} from "../../../models/professor";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {Group} from "../../../models/group";
import {Router} from "@angular/router";
import {DictionaryService} from "../../../services/dictionary.service";
import {AccountService} from "../../../services/accountService";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {PairService} from "../../../services/pair.service";
import {Pair} from "../../../models/shedule/pair";
import {Department} from "../../../models/department";
import {DepartmentShedule} from "../../../models/shedule/departmentShedule";

@Component({
    selector: 'list-professors-page',
    templateUrl: './show-schedule.component.html',
    styleUrls: ["./show-schedule.component.css"]
})

export class ShowScheduleComponent implements OnInit {

    public profs: Array<Professor> = new Array();
    public groups: Array<Group> = new Array();
    public department: Array<Department> = new Array();
    public RouteConstants = RouteConstants;
    public menuToggle: string = "professor";
    public pairList: Array<Pair> = null;
    public departmentSchedule: Array<DepartmentShedule>;

    public currentProfessor: Professor;
    public currentGroup: Group;
    public currentDepartment: Department;

    public showLoader: boolean = false;

    constructor(private router: Router,
                private pairService: PairService,
                private dictionaryService: DictionaryService,
                private accountService: AccountService) {
    }

    ngOnInit() {
        this.GetProfs();
        this.GetGroups();
        this.GetDepartments();
    }

    public update() {
        switch (this.menuToggle) {
            case "professor":
                this.getProfessorPair(this.currentProfessor);
                break;
            case "group":
                this.getGroupPair(this.currentGroup);
                break;
        }
    }

    public GetProfs() {
        this.accountService.GetProfessors()
            .subscribe((res: Array<Professor>) => {
                    this.profs = res;
                },
                error => console.error(error));
    }
    public GetGroups() {
        this.dictionaryService.GetGroups()
            .subscribe((res: PageResult) => {
                    this.groups = res.content;
                },
                error => console.error(error));
    }
    public GetDepartments() {
        this.dictionaryService.GetDepartments()
            .subscribe((res: PageResult) => {
                    this.department = res.content;
                },
                error => console.error(error));
    }

    public getProfessorPair(professor: Professor) {
        this.currentProfessor = professor;
        this.pairList = null;
        this.showLoader = true;
        this.pairService.GetPeofessorPair(professor.id).subscribe(
            pairs =>  {
                this.pairList = pairs;
                this.showLoader = false;
            }
        );
    }
    public getGroupPair(group: Group) {
        this.currentGroup = group;
        this.pairList = null;
        this.showLoader = true;
        this.pairService.GetGroupPair(group.id).subscribe(
            pairs =>  {
                this.pairList = pairs;
                this.showLoader = false;
            }
        );
    }
    public getDepartmentPair(department: Department) {
        this.currentDepartment = department;
        this.departmentSchedule = null;
        this.showLoader = true;
        this.pairService.GetDepartmentPair(department.id).subscribe(
            pairs =>  {
                this.departmentSchedule = pairs;
                this.showLoader = false;
            }
        );
    }

    public changeToogle(item: string) {
        this.currentProfessor = null;
        this.currentGroup = null;
        this.currentDepartment = null;

        this.pairList = null;
        this.departmentSchedule = null;
        this.menuToggle = item;
    }

}

