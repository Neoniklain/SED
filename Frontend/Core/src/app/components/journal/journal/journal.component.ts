import {Component, Injectable, Input, OnInit} from '@angular/core';
import {JournalService} from "../../../services/journal.service";
import {Journal, JournalCell, PointType} from "../../../models/journal/journal.model";
import {Pair} from "../../../models/shedule/pair";
import {NotificationService} from "../../../services/notification.service";
import {DatePipe} from "@angular/common";
import {SelectItem} from "primeng/api";
import {CertificationReport, CertificationStudent} from "../../../models/journal/certificationReport.model";
import {Lesson} from "../../../models/shedule/lesson";
import {SemesterNumberYear} from "../../../models/semesterNumberYear.model";

@Component({
    selector: 'journal',
    templateUrl: './journal.component.html',
    styleUrls: ['./journal.component.css']
})

@Injectable()
export class JournalComponent implements OnInit {

    @Input() lesson: Lesson;
    @Input() subgroupType: number = 0;
    @Input() semesterNumberYear: SemesterNumberYear;
    public journal: Journal;
    public month: number;
    public oldJournal: Journal = null;
    public header: Array<JournalHeader> = [];
    public monthHeader: Array<MonthHeader> = [];
    public currentMonth: string;
    public currentDay: Date;
    public showLoader: boolean = false;
    public datePipe = new DatePipe("ru");
    public selectStudentId: number = 0;

    public reportSatrtDate: Date;
    public reportEndDate: Date;
    public certGenerator: boolean = false;
    public certificationReport: CertificationReport;

    public historyDates = [];
    public selectHistoryDate: Date = null;

    public types: SelectItem[];
    public ru: any;

    constructor(private journalService: JournalService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.ru = {
            firstDayOfWeek: 1,
            dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
            dayNamesShort: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб"],
            dayNamesMin: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб"],
            monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
            monthNamesShort: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
            today: 'Сегодня',
            clear: 'Очистить'
        };
        this.types = [
            {label: 'Все', value: 0},
            {label: 'Подгруппа 1', value: 1},
            {label: 'Подгруппа 2', value: 2}
        ];

        this.currentDay = new Date(this.datePipe.transform(Date.now(), "yyyy-MM-ddTHH:mm:ss"));
        this.currentMonth = (Number.parseInt(this.datePipe.transform(Date.now(), 'MMMM')) - 1).toString();
        this.month = Number.parseInt(this.datePipe.transform(Date.now(), 'MM')) - 1;
        this.updateJournal();

    }

    updateJournal() {
        let lastJournal = new Journal();
        if (this.journal)
            lastJournal = JSON.parse(JSON.stringify(this.journal));
        this.journal = null;
        if (this.lesson.id !== 0) {
            this.showLoader = true;
            this.journalService.GetJournal(this.lesson.id, this.month, this.semesterNumberYear, this.selectHistoryDate).subscribe(
                result => {
                    this.journal = result.data;
                    this.sortHeader();
                    this.oldJournal = JSON.parse(JSON.stringify(this.journal));
                    this.getHistoryDates();
                    this.showLoader = false;
                }, error => {
                    this.journal = lastJournal;
                    this.showLoader = false;
                }
            );
        }
    }

    nextMonth() {
        this.month++;
        this.month = Number.parseInt(this.datePipe.transform(this.journal.comparison[0].date, 'MM'));
        this.updateJournal();
    }

    backMonth() {
        this.month--;
        this.month = Number.parseInt(this.datePipe.transform(this.journal.comparison[0].date, 'MM')) - 2;
        this.updateJournal();
    }

    sortHeader() {
        this.header = [];
        this.monthHeader = [];

        this.journal.comparison.forEach(x => x.date = this.createDate(x.date));
        this.journal.journalCell.forEach(x => x.date = this.createDate(x.date));

        for (let student of this.journal.students) {
            for (let comp of this.journal.comparison) {
                for (let point of comp.points) {
                    let find = this.journal.journalCell.find(cell =>
                        this.eqDate(comp.date, cell.date)
                        && point.type.name == cell.type.name
                        && point.pair.id == cell.pairId
                        && cell.studentId == student.student.id
                    );
                    if (!find) {
                        let newCell = new JournalCell();
                        newCell.date = this.createDate(comp.date);
                        newCell.type = point.type;
                        newCell.pairId = point.pair.id;
                        newCell.value = null;
                        newCell.studentId = student.student.id;
                        newCell.id = 0;
                        this.journal.journalCell.push(newCell);
                    }
                }
            }
        }

        // Создание заголовка дат
        this.setHeaderDates();

        // Создание заголовка месяцов
        for (let headDate of this.header) {
            let existMonthHeader = false;
            headDate.numbers.sort(function (a, b) {
                if (a.number < b.number)
                    return -1;
                if (a.number > b.number)
                    return 1;
                return 0;
            });
            for (let head of this.monthHeader) {
                if (head.date.getMonth() === headDate.date.getMonth()) {
                    head.dateLen = head.dateLen + headDate.numbers.length;
                    existMonthHeader = true;
                }
            }
            if (!existMonthHeader) {
                let curMonthHeader: MonthHeader = new MonthHeader();
                let temp = new Date(this.datePipe.transform(headDate.date, "yyyy-MM-ddTHH:mm:ss"));
                curMonthHeader.date = temp;
                curMonthHeader.dateLen = headDate.numbers.length;
                this.monthHeader.push(curMonthHeader);
            }
        }

    }

    setHeaderDates() {
        for (let comp of this.journal.comparison) {
            let temp: JournalHeader = new JournalHeader();
            temp.date = comp.date;
            for (let point of comp.points) {
                let newNumber = new JournalHeaderNumber();
                newNumber.number = point.pair.pairNumber;
                newNumber.types.push(new JournalHeaderType(point.type, point.pair));
                temp.numbers.push(newNumber);
            }
            this.header.push(temp);
        }

        for (let comp of this.header) {
            let newNum = [];
            for (let x of comp.numbers) {
                let find = newNum.find(y => y.number == x.number);
                if (!find) {
                    newNum.push(x);
                } else {
                    let newFind = newNum.find(y => y.number == find.number);
                    let index = newNum.indexOf(newFind);
                    for (let t of x.types)
                        newFind.types.push(t);
                    newNum.splice(index, 1, newFind);
                }
            }
            comp.numbers = newNum;
        }
    }

    getHeaderLength() {
        let result = 0;
        for (let head of this.header) {
            for (let num of head.numbers) {
                for (let type of num.types) {
                    if (type.pair.subgroup == this.subgroupType || this.subgroupType == 0 || type.pair.subgroup == 0)
                        result += 1;
                }
            }
        }
        return result;
    }

    getDateLen(head: JournalHeader) {
        let result = 0;
        for (let num of head.numbers) {
            for (let t of num.types) {
                if (t.pair.subgroup == this.subgroupType || this.subgroupType == 0 || t.pair.subgroup == 0)
                    result += 1;
            }
        }
        return result;
    }

    getForDateLenForNumber(numberJournal: JournalHeaderNumber) {
        let result = 0;
        for (let t of numberJournal.types) {
            if (t.pair.subgroup == this.subgroupType || this.subgroupType == 0 || t.pair.subgroup == 0)
                result += 1;
        }
        return result;
    }

    save() {
        let journalForSend: Journal = new Journal();
        journalForSend.comparison = this.journal.comparison;
        journalForSend.students = this.journal.students;
        journalForSend.lesson = this.journal.lesson;
        journalForSend.journalCell = [];
        for (let j of this.journal.journalCell) {
            if (j.id == 0 && j.value == 0)
                continue;

            let find = this.oldJournal.journalCell.findIndex(x =>
                x.pairId == j.pairId
                && this.eqDate(this.createDate(x.date), j.date)
                && x.studentId == j.studentId
                && x.type.id == j.type.id
            );

            if (find != -1 && this.oldJournal.journalCell[find].value != j.value) {
                j.wasChange = false;
                journalForSend.journalCell.push(j);
            }
        }
        this.showLoader = true;
        this.journalService.Save(journalForSend).subscribe(
            result => {
                this.showLoader = false;
                this.oldJournal = JSON.parse(JSON.stringify(this.journal));
                this.notificationService.FromStatus(result);
                this.getHistoryDates();
            }, error => console.error(error)
        );
    }

    changeHistoryDate(event) {
        this.selectHistoryDate = event;
        this.updateJournal();
    }

    getCertification() {
        if (this.reportSatrtDate && this.reportEndDate) {
            let start = this.datePipe.transform(this.reportSatrtDate, "yyyy-MM-dd");
            let end = this.datePipe.transform(this.reportEndDate, "yyyy-MM-dd");
            this.journalService.GetJournalCertificationReport(this.journal.lesson.id, start, end, this.semesterNumberYear).subscribe(
                result => {
                    this.certificationReport = result.data;
                }, error => {
                }
            );

        }
    }

    isCurrentDate(day: Date) {
        return (day.getDate() === this.currentDay.getDate());
    }

    setCellValue(element: number, cell: JournalCell) {
        let find = this.oldJournal.journalCell.findIndex(x =>
            x.pairId == cell.pairId
            && this.eqDate(this.createDate(x.date), cell.date)
            && x.studentId == cell.studentId
            && x.type.id == cell.type.id
        );

        if (find != -1) {
            if (this.oldJournal.journalCell[find].value != element) {
                cell.wasChange = true;
            }
            if (this.oldJournal.journalCell[find].value == element ||
                (this.oldJournal.journalCell[find].value == null && element == 0)
            ) {
                cell.wasChange = false;
            }
        }

        if (element == 0)
            cell.value = null;
        else
            cell.value = element;
        this.selectStudentId = 0;
    }

    eqDate(date1: Date, date2: Date) {
        if (date1.getDate() === date2.getDate()
            && date1.getMonth() === date2.getMonth()
        ) {
            return true;
        }
        return false;
    }

    getEventsSummValue(car: CertificationStudent) {
        let summ = 0;
        for (let s of car.eventValue) {
            summ += s.value;
        }
        return summ;
    }

    createDate(date: Date): Date {
        return new Date(this.datePipe.transform(date, "yyyy-MM-dd"));
    }

    getHistoryDates() {
        if (this.journal) {
            this.journalService.GetJournalHistoryDate(this.journal.lesson.id, this.semesterNumberYear).subscribe(
                result => {
                    this.historyDates = [];
                    for (let d of result.data) {
                        let newItem = {
                            label: this.datePipe.transform(d, "dd.MM.yyyy"),
                            value: this.datePipe.transform(d, "dd.MM.yyyy")
                        };
                        this.historyDates.push(newItem);
                    }
                }, error => console.error(error)
            );
        }
    }
}

class JournalHeader {
    public date: Date;
    public numbers: Array<JournalHeaderNumber>;

    constructor() {
        this.numbers = [];
    }
}

class JournalHeaderNumber {
    public number: number;
    public types: Array<JournalHeaderType>;

    constructor() {
        this.number = 0;
        this.types = [];
    }
}

class JournalHeaderType {
    public type: PointType;
    public pair: Pair;

    constructor(type: PointType, pair: Pair) {
        this.type = type;
        this.pair = pair;
    }
}

class MonthHeader {
    public date: Date;
    public dateLen: number;
}