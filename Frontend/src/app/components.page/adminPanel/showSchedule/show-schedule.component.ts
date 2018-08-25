import {Component, OnInit} from "@angular/core";
import {Professor} from "../../../models/account/professor";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {Group} from "../../../models/shedule/group";
import {Router} from "@angular/router";
import {DictionaryService} from "../../../services/dictionary.service";
import {AccountService} from "../../../services/accountService";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {ScheduleService} from "../../../services/schedule.service";
import {Pair} from "../../../models/shedule/pair";
import {Department} from "../../../models/shedule/department";
import {DepartmentShedule} from "../../../models/shedule/departmentShedule";
import {ResponseStatus} from "../../../models/additional/responseStatus";
import {NotificationService} from "../../../services/notification.service";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {User} from "../../../models/account/user.model";
import {PairType} from "../../../models/shedule/pairType";
import {Discipline} from "../../../models/shedule/discipline";
import {isUndefined} from "util";
import {Room} from "../../../models/shedule/room.model";

@Component({
    selector: 'list-professors-page',
    templateUrl: './show-schedule.component.html',
    styleUrls: ["./show-schedule.component.css"]
})

export class ShowScheduleComponent implements OnInit {

    public profs: Array<Professor> = new Array();
    public groups: Array<Group> = new Array();
    public departments: Array<Department> = new Array();
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
                private ScheduleService: ScheduleService,
                private dictionaryService: DictionaryService,
                private accountService: AccountService,
                ) {
    }

    ngOnInit() {
    }

    public update() {
        switch (this.menuToggle) {
            case "professor":
                this.getProfessorPair();
                break;
            case "group":
                this.getGroupPair();
                break;
        }
    }

    public GetProfessors(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        };
        let temp: Array<Professor> = new Array();
        this.dictionaryService.Get(Dictionary.professors, filter).subscribe(
            result => {
                console.log("result", result);
                temp = result.content;
                if (temp.length > 0) {
                    this.profs = temp;
                }
                else {
                    this.profs = [];
                }
            }, error => console.error(error)
        );
    }
    public GetGroups(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        }
        let temp: Array<Group> = new Array();
        this.dictionaryService.Get(Dictionary.groups, filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.groups = temp;
                else
                    this.groups = [];
            }, error => console.error(error)
        );
    }
    public GetDepartments(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        }
        let temp: Array<Department> = new Array();
        this.dictionaryService.Get(Dictionary.departments, filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.departments = temp;
                else
                    this.departments = [];
            }, error => console.error(error)
        );
    }

    public getProfessorPair() {
        if (this.currentProfessor == null && this.currentProfessor.id == 0)
            return;
        this.pairList = null;
        this.showLoader = true;
        this.ScheduleService.GetPeofessorPair(this.currentProfessor.id).subscribe(
            result =>  {
                this.templatePair = new Pair();
                this.templatePair.lesson.professor = this.currentProfessor;
                this.pairList = result.data;
                this.showLoader = false;
            }
        );
    }
    public getGroupPair() {
        if (this.currentGroup == null && this.currentGroup.id == 0)
            return;
        this.pairList = null;
        this.showLoader = true;
        this.ScheduleService.GetGroupPair(this.currentGroup.id).subscribe(
            result =>  {
                this.templatePair = new Pair();
                this.templatePair.lesson.group = this.currentGroup;
                this.pairList = result.data.pairs;
                this.showLoader = false;
            }
        );
    }

    public getDepartmentPair() {
        if (this.currentDepartment == null && this.currentDepartment.id == 0)
            return
        this.departmentSchedule = null;
        this.showLoader = true;
        this.ScheduleService.GetDepartmentPair(this.currentDepartment.id).subscribe(
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

