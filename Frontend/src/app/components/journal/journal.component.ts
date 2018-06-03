import {Component, Injectable, Input, OnChanges, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authService";
import {JournalService} from "../../services/journal.service";
import {Journal, JournalCell, PointType} from "../../models/journal/journal.model";
import {PairService} from "../../services/pair.service";
import {Pair} from "../../models/shedule/pair";
import {NotificationService} from "../../services/notification.service";
import {isUndefined} from "util";
import {DatePipe} from "@angular/common";
import {PairNumber} from "../../models/shedule/pairNumber.model";

@Component({
    selector: 'journal',
    templateUrl: './journal.component.html',
    styleUrls: ['./journal.component.css']
})
@Injectable()
export class JournalComponent implements OnInit {

    @Input() journal: Journal;
    public header: Array<JournalHeader> = new Array<JournalHeader>();
    public monthHeader: Array<MonthHeader> = new Array<MonthHeader>();
    public currentMonth: string;
    public currentDay: Date;
    public datePipe = new DatePipe("ru");

    constructor(private authenticationService: AuthenticationService,
                private journalService: JournalService,
                private pairService: PairService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.currentDay = new Date(this.datePipe.transform(Date.now(), "yyyy-MM-ddTHH:mm:ss"));
        this.currentMonth = (Number.parseInt(this.datePipe.transform(Date.now(), 'M')) - 1).toString();
        if (this.journal != null && !isUndefined(this.journal))
            this.sortHeader();
    }

    nextMonth() {
        let checkMonth = Number.parseInt(this.currentMonth) + 1;
        for (let date of this.journal.dates) {
            let checkDay = (Number.parseInt(this.datePipe.transform(date, 'M')) - 1).toString();
            if (checkDay === checkMonth.toString()) {
                this.currentMonth = checkMonth.toString();
                return true;
            }
        }
        return true;
    }

    backMonth() {
        let checkMonth = Number.parseInt(this.currentMonth) - 1;
        for (let date of this.journal.dates) {
            let checkDay = (Number.parseInt(this.datePipe.transform(date, 'M')) - 1).toString();
            if (checkDay === checkMonth.toString()) {
                this.currentMonth = checkMonth.toString();
                return true;
            }
        }
        return false;
    }

    sortHeader() {
        this.header = [];
        this.monthHeader = [];

        for (let d of this.journal.dates) {
            let temp = new Date(this.datePipe.transform(d, "yyyy-MM-ddTHH:mm:ss"));
            d = temp;
        }

        for (let cell of this.journal.journalCell) {
            let temp = new Date(this.datePipe.transform(cell.date, "yyyy-MM-ddTHH:mm:ss"));
            cell.date = temp;
        }

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

        this.header.sort(
            function (a, b) {
                if (a.date < b.date)
                    return -1;
                if (a.date > b.date)
                    return 1;
                return 0;
            }
        );
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

        this.monthHeader.sort(
            function (a, b) {
                if (a.date < b.date)
                    return -1;
                if (a.date > b.date)
                    return 1;
                return 0;
            }
        );

    }

    setHeaderDates() {
        for (let date of this.journal.dates) {
            let temp: JournalHeader = new JournalHeader();
            let tempDate = new Date(this.datePipe.transform(date, "yyyy-MM-ddTHH:mm:ss"));
            temp.date = tempDate;
            for (let cell of this.findCellsForDay(tempDate)) {
                if (cell.student.id === this.journal.students[0].id
                    && this.eqDate(cell.date, tempDate)) {
                    temp.types.push(new JournalHeaderType(cell.type, cell.pair));
                }
            }
            this.header.push(temp);
        }
    }

    findCellsForDay(date: Date): Array<JournalCell> {
        let cells = new Array<JournalCell>();
        for (let cell of this.journal.journalCell) {
            if (this.eqDate(cell.date, date)) {
                cells.push(cell);
            }
        }
        return cells;
    }

    save() {
        this.journalService.Save(this.journal).subscribe(
            result => {
                this.notificationService.FromStatus(result);
            }, error => console.log(error)
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










