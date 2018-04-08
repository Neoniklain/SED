import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {JournalService} from "../../../services/journal.service";
import {Journal} from "../../../models/journal/journal.model";
import {SheduleService} from "../../../services/shedule.service";
import {Pair} from "../../../models/shedule/pair";

@Component({
    selector: 'schedule-week',
    templateUrl: './weekSchedule.component.html',
    styleUrls: ['./weekSchedule.component.css']
})
@Injectable()
export class WeekScheduleComponent implements OnInit {

    @Output() clickPair = new EventEmitter<any>();
    @Input()
    public pairs: Array<Pair>;
    public days = ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"];
    public lessonsTime = [
        {number: 1, Start: "8:30", End: "10:05"},
        {number: 2, Start: "10:15", End: "11:50"},
        {number: 3, Start: "12:15", End: "13:50"},
        {number: 4, Start: "14:00", End: "15:35"},
        {number: 5, Start: "16:00", End: "17:35"},
        {number: 6, Start: "17:45", End: "19:20"}
    ]

    constructor(private authenticationService: AuthenticationService,
                private journalService: JournalService,
                private sheduleService: SheduleService,
                private router: Router) { }

    ngOnInit(): void {}

    onClick(pair) {
        this.clickPair.emit(pair);
    }

}
