import {Component, EventEmitter, Injectable, OnInit, Output} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {Journal} from "../../../models/journal/journal.model";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {JournalService} from "../../../services/journal.service";
import {ScheduleService} from "../../../services/schedule.service";
import {AccountService} from "../../../services/accountService";
import {Professor} from "../../../models/account/professor";
import {DatePipe} from "@angular/common";
import {CertificationReport, CertificationStudent} from "../../../models/journal/certificationReport.model";

@Component({
    selector: 'journal-page',
    templateUrl: './journal-page.component.html',
    styleUrls: ['./journal-page.component.css']
})
@Injectable()
export class JournalPageComponent implements OnInit {

    @Output() toogleViewMenu: EventEmitter<any> = new EventEmitter();
    public user: User;
    public professor: Professor;
    public journal: Journal;
    public pairs: Array<Pair> = [];
    public showLoader: boolean = false;
    public month: number;
    public lastMonth: number;
    public datePipe = new DatePipe("ru");
    public lastPair: Pair;

    public reportSatrtDate: Date;
    public reportEndDate: Date;
    public certGenerator: boolean = false;
    public certificationReport: CertificationReport;

    constructor(private authenticationService: AuthenticationService,
                private journalService: JournalService,
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
                this.accountService.GetProfessorByUser(this.user.id).subscribe(
                    resultProf => {
                        this.professor = resultProf.data;
                        if (this.professor !== null) {
                            this.ScheduleService.GetPeofessorPair(this.professor.id).subscribe(
                                result => {
                                    this.showLoader = false;
                                    this.pairs = result.data;
                                }
                            );
                        }
                    }
                );
            });
    }

    getCertification() {
        if (this.reportSatrtDate && this.reportEndDate) {
            let start = this.datePipe.transform(this.reportSatrtDate, "yyyy-MM-dd");
            let end = this.datePipe.transform(this.reportEndDate, "yyyy-MM-dd");
            this.journalService.GetJournalCertificationReport(this.lastPair.lesson.id, start, end).subscribe(
                result => {
                    this.certificationReport = result.data;
                    console.log("result.data", result.data);
                    console.log("this.certificationReport", this.certificationReport);
                }, error => {
                }
            );

        }
    }

    changeMonth(event) {
        this.lastMonth = this.month;
        this.month = event;
        this.updateJournal();
    }

    onClick(pair: Pair) {
        this.lastPair = pair;
        this.updateJournal();
    }

    updateJournal() {
        let lastJournal = new Journal();
        if (this.journal)
            lastJournal = JSON.parse(JSON.stringify(this.journal));
        this.journal = null;
        if (this.lastPair.id !== 0) {
            this.showLoader = true;
            this.journalService.GetJournal(this.lastPair.lesson.id, this.month).subscribe(
                result => {
                    for (let key in result.data.сomparison) {
                    }
                    if (!this.lastMonth) {
                        this.toogleViewMenu.emit();
                    }
                    this.journal = result.data;
                    this.showLoader = false;
                }, error => {
                    this.journal = lastJournal;
                    this.month = this.lastMonth;
                    this.showLoader = false;
                }
            );
        }
    }

    getEventsSummValue(car: CertificationStudent) {
        let summ = 0;
        for (let s of car.eventValue) {
            summ += s.value;
        }
        return summ;
    }

    back() {
        this.lastMonth = null;
        this.journal = null;
        this.toogleViewMenu.emit();
    }

}
