import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/account/user.model";
import {AuthenticationService} from "../../services/authService";
import {JournalService} from "../../services/journal.service";
import {Journal} from "../../models/journal/journal.model";
import {SheduleService} from "../../services/shedule.service";
import {Pair} from "../../models/shedule/pair";

@Component({
    selector: 'journal-page',
    templateUrl: './journal.component.html',
    styleUrls: ['./journal.component.css']
})
@Injectable()
export class JournalComponent implements OnInit {

    public user: User;
    public journal: Journal;
    public pairs: Array<Pair> = new Array<Pair>();

    constructor(private authenticationService: AuthenticationService,
                private journalService: JournalService,
                private sheduleService: SheduleService,
                private router: Router) {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res;
                this.sheduleService.GetPeofessorPair(this.user.id).subscribe(
                    result => {
                        this.pairs = result;
                    }, error => console.error(error)
                );
            },
            error => {
                if (error.statusText === "Forbidden")
                    this.router.navigate(['/404']);
            });
    }

    ngOnInit(): void { }

    onClick(pair: Pair) {
        this.journalService.GetJournal(this.user.id, pair.group.id).subscribe(
            result => {
                this.journal = result;
            }, error => console.log(error)
        );
        console.log(pair);
    }

    back() {
        this.journal = null;
    }

    clearField(cell) {
        if (cell.mark === 0) cell.mark = '';
    }

    cancelField(cell) {
        if (cell.mark === '') cell.mark = 0;
    }

}
