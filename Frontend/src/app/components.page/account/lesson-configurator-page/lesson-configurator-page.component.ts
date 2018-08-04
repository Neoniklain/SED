import {Component, Injectable, OnInit} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {Journal} from "../../../models/journal/journal.model";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {JournalService} from "../../../services/journal.service";
import {PairService} from "../../../services/pair.service";
import {AccountService} from "../../../services/accountService";
import {Professor} from "../../../models/account/professor";

@Component({
    selector: 'lesson-configurator-page',
    templateUrl: './lesson-configurator-page.component.html',
    styleUrls: ['./lesson-configurator-page.component.css']
})
@Injectable()
export class LessonConfiguratorPageComponent implements OnInit {

    public user: User;
    public professor: Professor;
    public selectPair: Pair;
    public pairs: Array<Pair> = new Array<Pair>();
    public showLoader: boolean = false;


    constructor(private authenticationService: AuthenticationService,
                private accountService: AccountService,
                private pairService: PairService) {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                this.showLoader = true;
                this.accountService.GetProfessorByUser(this.user.id).subscribe(
                    resultProf => {
                        this.professor = resultProf.data;
                            this.pairService.GetPeofessorPair(this.professor.id).subscribe(
                            result => {
                                this.showLoader = false;
                                this.pairs = result.data;
                            }
                        );
                    }
                );
            });
    }

    ngOnInit(): void {
        this.selectPair = null;
    }

    onClick(pair: Pair) {
        if (pair.id !== 0)
            this.selectPair = pair;
    }

    back() {
        this.selectPair = null;
    }

}
