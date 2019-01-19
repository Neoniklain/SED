import {Component, EventEmitter, Injectable, OnInit, Output} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/auth.service";
import {ScheduleService} from "../../../services/schedule.service";
import {AccountService} from "../../../services/account.service";
import {DatePipe} from "@angular/common";
import {Roles} from "../../../models/account/role.model";
import {Student} from "../../../models/account/student";

@Component({
    selector: 'journal-page',
    templateUrl: './journal-page.component.html',
    styleUrls: ['./journal-page.component.css']
})
@Injectable()
export class JournalPageComponent implements OnInit {

    @Output() toogleViewMenu: EventEmitter<any> = new EventEmitter();
    public user: User;
    public pairs: Array<Pair> = [];
    public showLoader: boolean = false;
    public month: number;
    public lastMonth: number;
    public datePipe = new DatePipe("ru");
    public lastPair: Pair;
    public Roles = Roles;

    constructor(private authenticationService: AuthenticationService,
                private accountService: AccountService,
                private ScheduleService: ScheduleService) {
    }

    ngOnInit(): void {
        this.month = Number.parseInt(this.datePipe.transform(Date.now(), 'M')) - 1;
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                this.showLoader = true;
                if (this.user.roles.find(x => x.roleName == Roles.Professor.toString()))
                    this.getProffesorPair();
                else if (this.user.roles.find(x => x.roleName == Roles.Student.toString()))
                    this.getStudentPair();
            });
    }

    public getProffesorPair() {
        this.accountService.GetProfessorByUser(this.user.id).subscribe(
            resultProf => {
                let professor = resultProf.data;
                if (professor !== null) {
                    this.ScheduleService.GetPeofessorPair(professor.id).subscribe(
                        result => {
                            this.showLoader = false;
                            this.pairs = result.data;
                        }
                    );
                }
            }
        );
    }

    public getStudentPair() {
        this.accountService.GetStudentByUser(this.user.id).subscribe(
            resultStud => {
                let student: Student = resultStud.data;
                if (student) {
                    this.ScheduleService.GetGroupPair(student.group.id).subscribe(
                        result => {
                            this.showLoader = false;
                            this.pairs = result.data;
                        }
                    );
                }
            }
        );
    }

    onClick(pair: Pair) {
        this.lastPair = pair;
        this.toogleViewMenu.emit();
    }


    back() {
        this.lastPair = null;
        this.toogleViewMenu.emit();
    }

}
