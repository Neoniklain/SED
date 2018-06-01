import {Component, OnInit} from "@angular/core";
import {Professor} from "../../../models/account/professor";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {Group} from "../../../models/shedule/group";
import {Router} from "@angular/router";
import {DictionaryService} from "../../../services/dictionary.service";
import {AccountService} from "../../../services/accountService";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {PairService} from "../../../services/pair.service";
import {Pair} from "../../../models/shedule/pair";
import {Department} from "../../../models/shedule/department";
import {DepartmentShedule} from "../../../models/shedule/departmentShedule";
import {ResponseStatus} from "../../../models/additional/responseStatus";
import {NotificationService} from "../../../services/notification.service";

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
    public templatePair: Pair;
    public departmentSchedule: Array<DepartmentShedule>;

    public currentProfessor: Professor;
    public currentGroup: Group;
    public currentDepartment: Department;

    public showLoader: boolean = false;

    constructor(private notification: NotificationService,
                private router: Router,
                private pairService: PairService,
                private dictionaryService: DictionaryService,
                private accountService: AccountService,
                ) {
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
            .subscribe((res: ResponseStatus) => {
                    this.profs = res.data;
                });
    }
    public GetGroups() {
        this.dictionaryService.GetGroups()
            .subscribe((res: PageResult) => {
                    this.groups = res.content;
                });
    }
    public GetDepartments() {
        this.dictionaryService.GetDepartments()
            .subscribe((res: PageResult) => {
                    this.department = res.content;
                });
    }

    public getProfessorPair(professor: Professor) {
        this.currentProfessor = professor;
        this.pairList = null;
        this.showLoader = true;
        this.pairService.GetPeofessorPair(professor.id).subscribe(
            result =>  {
                this.templatePair = new Pair();
                this.templatePair.professor = this.currentProfessor;
                this.pairList = result.data;
                this.showLoader = false;
            }
        );
    }
    public getGroupPair(group: Group) {
        this.currentGroup = group;
        this.pairList = null;
        this.showLoader = true;
        this.pairService.GetGroupPair(group.id).subscribe(
            result =>  {
                console.log(result.data);
                this.templatePair = new Pair();
                this.templatePair.group = this.currentGroup;
                this.pairList = result.data.pairs;
                this.showLoader = false;
            }
        );
    }

    public getDepartmentPair(department: Department) {
        this.currentDepartment = department;
        this.departmentSchedule = null;
        this.showLoader = true;
        this.pairService.GetDepartmentPair(department.id).subscribe(
            result =>  {
                this.departmentSchedule = result.data.lines;
                this.update();
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

