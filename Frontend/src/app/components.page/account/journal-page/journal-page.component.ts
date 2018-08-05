import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Pair} from "../../../models/shedule/pair";
import {Journal} from "../../../models/journal/journal.model";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {JournalService} from "../../../services/journal.service";
import {ScheduleService} from "../../../services/schedule.service";
import {NotificationService} from "../../../services/notification.service";
import {AccountService} from "../../../services/accountService";
import {Professor} from "../../../models/account/professor";

@Component({
    selector: 'journal-page',
    templateUrl: './journal-page.component.html',
    styleUrls: ['./journal-page.component.css']
})
@Injectable()
export class JournalPageComponent implements OnInit {

    public user: User;
    public professor: Professor;
    public journal: Journal;
    public pairs: Array<Pair> = new Array<Pair>();
    public showLoader: boolean = false;

    constructor(private authenticationService: AuthenticationService,
                private journalService: JournalService,
                private accountService: AccountService,
                private ScheduleService: ScheduleService) {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                this.showLoader = true;
                this.accountService.GetProfessorByUser(this.user.id).subscribe(
                    resultProf => {
                        this.professor = resultProf.data;
                            this.ScheduleService.GetPeofessorPair(this.professor.id).subscribe(
                            result => {
                                this.showLoader = false;
                                this.pairs = result.data;
                            }
                        );
                    }
                );
            });
    }

    ngOnInit(): void { }

    onClick(pair: Pair) {
        if (pair.id !== 0) {
            this.showLoader = true;
            this.journalService.GetJournal(pair.lesson.id).subscribe(
                result => {
                    this.journal = result.data;
                    this.showLoader = false;
                }, error => console.error(error)
            );
        }
    }

    back() {
        this.journal = null;
    }

}
