import {Component, Input, OnInit} from "@angular/core";
import {Professor} from "../../../models/account/professor";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {Group} from "../../../models/shedule/group";
import {Router} from "@angular/router";
import {DictionaryService} from "../../../services/dictionary.service";
import {ScheduleService} from "../../../services/schedule.service";
import {Pair} from "../../../models/shedule/pair";
import {Department} from "../../../models/shedule/department";
import {DepartmentShedule} from "../../../models/shedule/departmentShedule";
import {NotificationService} from "../../../services/notification.service";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {isUndefined} from "util";
import {LazyLoadEvent} from "primeng/api";

@Component({
        selector: 'schedule-page',
        templateUrl: './show-schedule.component.html',
        styleUrls: ["./show-schedule.component.css"]
    })

    export class ShowScheduleComponent implements OnInit {

    @Input() editable: boolean = false;
    public RouteConstants = RouteConstants;
    public menuToggle: string = "professor";
    public pairList: Array<Pair> = null;
    public templatePair: Pair;
    public departmentSchedule: Array<DepartmentShedule>;

    public profs: Array<Professor> = [];
    public groups: Array<Group> = [];
    public departments: Array<Department> = [];

    public lastprofsFilter: LazyLoadEvent = { globalFilter: "&7&" };
    public lastgroupsFilter: LazyLoadEvent = { globalFilter: "&7&" };
    public lastdepartmentsFilter: LazyLoadEvent = { globalFilter: "&7&" };

    public currentProfessor: Professor;
    public currentGroup: Group;
    public currentDepartment: Department;

    public showLoader: boolean = false;

    constructor(private notification: NotificationService,
                private router: Router,
                private ScheduleService: ScheduleService,
                private dictionaryService: DictionaryService,
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
        if (this.profs.length == 0 || this.lastprofsFilter.globalFilter != event.query.substring(0, 60)) {
            if (!isUndefined(event.query))
                this.lastprofsFilter = { globalFilter: event.query.substring(0, 60) };

            this.dictionaryService.Get(Dictionary.professors, this.lastprofsFilter).subscribe(
                result => {
                    if (result.content.length > 0)
                        this.profs = result.content;
                }
            );
        } else {
            this.profs = JSON.parse(JSON.stringify(this.profs));
        }
    }

    public GetGroups(event: any) {
        if (this.groups.length == 0 || this.lastgroupsFilter.globalFilter != event.query.substring(0, 60)) {
            if (!isUndefined(event.query))
                this.lastgroupsFilter = { globalFilter: event.query.substring(0, 60) };

            this.dictionaryService.Get(Dictionary.groups, this.lastgroupsFilter).subscribe(
                result => {
                    if (result.content.length > 0)
                        this.groups = result.content;
                }
            );
        } else {
            this.groups = JSON.parse(JSON.stringify(this.groups));
        }
    }

    public GetDepartments(event: any) {
        if (this.departments.length == 0 || this.lastdepartmentsFilter.globalFilter != event.query.substring(0, 60)) {
            if (!isUndefined(event.query))
                this.lastdepartmentsFilter = { globalFilter: event.query.substring(0, 60) };

            this.dictionaryService.Get(Dictionary.departments, this.lastdepartmentsFilter).subscribe(
                result => {
                    if (result.content.length > 0)
                        this.departments = result.content;
                }
            );
        } else {
            this.departments = JSON.parse(JSON.stringify(this.departments));
        }
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

