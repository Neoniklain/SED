import {Component, Injectable, Input, OnChanges, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../services/authService";
import {JournalService} from "../../../services/journal.service";
import {Journal, JournalCell, PointType} from "../../../models/journal/journal.model";
import {ScheduleService} from "../../../services/schedule.service";
import {Pair} from "../../../models/shedule/pair";
import {NotificationService} from "../../../services/notification.service";
import {isUndefined} from "util";
import {DatePipe} from "@angular/common";
import {PairNumber} from "../../../models/shedule/pairNumber.model";

@Component({
    selector: 'journal',
    templateUrl: './journal.component.html',
    styleUrls: ['./journal.component.css']
})
@Injectable()
export class JournalComponent implements OnInit {

    @Input() journal: Journal;
    public header: Array<JournalHeader> = [];
    public monthHeader: Array<MonthHeader> = [];
    public currentMonth: string;
    public currentDay: Date;
    public showLoader: boolean = false;
    public datePipe = new DatePipe("ru");

    constructor(private authenticationService: AuthenticationService,
                private journalService: JournalService,
                private ScheduleService: ScheduleService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        if (this.journal != null && !isUndefined(this.journal))
            this.sortHeader();
        this.currentDay = new Date(this.datePipe.transform(Date.now(), "yyyy-MM-ddTHH:mm:ss"));
        if (this.monthHeader
            .map( x => x.date.getMonth())
            .indexOf(this.currentDay.getMonth()) !== -1) {
            this.currentMonth = (Number.parseInt(this.datePipe.transform(Date.now(), 'M')) - 1).toString();
        } else {
            this.currentMonth = (this.monthHeader.map( x => x.date.getMonth())[0]).toString();
        }
    }

    nextMonth() {
        let currentMonth = Number.parseInt(this.currentMonth);
        for (let month of this.monthHeader.map(x => x.date.getMonth())) {
            if (month > currentMonth) {
                this.currentMonth = month.toString();
                break;
            }
        }
    }

    backMonth() {
        let currentMonth = Number.parseInt(this.currentMonth);
        for (let month of this.monthHeader.sort(this.sortBigToSmallDate).map(x => x.date.getMonth())) {
            if (month < currentMonth) {
                this.currentMonth = month.toString();
                break;
            }
        }
        this.monthHeader.sort(this.sortSmallToBigDate);
    }

    sortHeader() {
        this.header = [];
        this.monthHeader = [];
        this.journal.dates.forEach(x => x = this.createDate(x));
        this.journal.journalCell.forEach(x => x.date = this.createDate(x.date));

        this.journal.journalCell.sort(
            function (a, b) {
                if (a.date < b.date)
                    return -1;
                if (a.date > b.date)
                    return 1;

                if (a.pair.pairNumber < b.pair.pairNumber)
                    return -1;
                if (a.pair.pairNumber > b.pair.pairNumber)
                    return 1;

                if (a.type.name !== "Посещение")
                    return 1;
                if (a.type.name === "Посещение")
                    return -1;

                return 0;
            }
        );

        // Создание заголовка дат
        this.setHeaderDates();

        this.header.sort(this.sortSmallToBigDate);

        // Создание заголовка месяцов
        for (let headDate of this.header) {
            let existMonthHeader = false;
            for (let head of this.monthHeader) {
                if (head.date.getMonth() === headDate.date.getMonth()) {
                    head.dateLen = head.dateLen + headDate.types.length;
                    existMonthHeader = true;
                }
            }
            if (!existMonthHeader) {
                let curMonthHeader: MonthHeader = new MonthHeader();
                let temp = new Date(this.datePipe.transform(headDate.date, "yyyy-MM-ddTHH:mm:ss"));
                curMonthHeader.date = temp;
                curMonthHeader.dateLen = headDate.types.length;
                this.monthHeader.push(curMonthHeader);
            }
        }

        this.monthHeader.sort(this.sortSmallToBigDate);

    }

    setHeaderDates() {
        for (let date of this.journal.dates) {
            let temp: JournalHeader = new JournalHeader();
            temp.date = this.createDate(date);
            for (let cell of this.findCellsForDay(temp.date)) {
                if (cell.student.id === this.journal.students[0].id
                    && this.eqDate(cell.date, temp.date)) {
                    temp.types.push(new JournalHeaderType(cell.type, cell.pair));
                }
            }
            this.header.push(temp);
        }
    }

    findCellsForDay(date: Date): Array<JournalCell> {
        let cells = [];
        for (let cell of this.journal.journalCell) {
            if (this.eqDate(cell.date, date)) {
                cells.push(cell);
            }
        }
        return cells;
    }

    save() {
        this.showLoader = true;
        this.journalService.Save(this.journal).subscribe(
            result => {
                this.showLoader = false;
                this.notificationService.FromStatus(result);
            }, error => console.error(error)
        );
    }

    isCurrentDate(day: Date) {
        return (
            day.getDate() === this.currentDay.getDate()
            && day.getMonth() === this.currentDay.getMonth());
    }

    isPairNow(pair: Pair) {
        let currentDate = Date.now();
        let pairNumber = new PairNumber(pair.pairNumber);
        let start = pairNumber.getTime().start;
        let end = pairNumber.getTime().end;
        if (this.datePipe.transform(currentDate, "H") >= this.datePipe.transform(start, "H")
            && this.datePipe.transform(currentDate, "H") <= this.datePipe.transform(end, "H")
            && this.datePipe.transform(currentDate, "m") >= this.datePipe.transform(start, "m")
            && this.datePipe.transform(currentDate, "m") <= this.datePipe.transform(end, "m")
        ) {
            return true;
        }
    }

    clearField(cell) {
        if (cell.value === 0) cell.value = '';
    }

    cancelField(cell) {
        if (cell.value === '') cell.value = 0;
    }

    hoverTdInput(element, cell) {
        element.focus();
        this.clearField(cell);
    }

    leaveTdInput(element, cell) {
        element.blur();
        this.cancelField(cell);
    }

    isCurrentMonth(day: Date) {
        return (day.getMonth() === this.currentDay.getMonth());
    }

    inMonthPeriodDate(day: Date) {
        return (day.getMonth() === Number.parseInt(this.currentMonth));
    }

    eqDate(date1: Date, date2: Date) {
        if (date1.getDate() === date2.getDate()
            && date1.getMonth() === date2.getMonth()
        ) {
            return true;
        }
        return false;
    }

    sortSmallToBigDate(a, b) {
        if (a.date < b.date)
            return -1;
        if (a.date > b.date)
            return 1;
        return 0;
    }
    sortBigToSmallDate(a, b) {
        if (a.date > b.date)
            return -1;
        if (a.date < b.date)
            return 1;
        return 0;
    }
    createDate(date: Date): Date {
        return new Date(this.datePipe.transform(date, "yyyy-MM-ddTHH:mm:ss"));
    }

}

class JournalHeader {
    public date: Date;
    public types: Array<JournalHeaderType>;

    constructor() {
        this.types = new Array();
    }
}

class JournalHeaderType {
    public type: PointType;
    public pair: Pair;

    constructor( type: PointType, pair: Pair) {
        this.type = type;
        this.pair = pair;
    }
}

class MonthHeader {
    public date: Date;
    public dateLen: number;
}










