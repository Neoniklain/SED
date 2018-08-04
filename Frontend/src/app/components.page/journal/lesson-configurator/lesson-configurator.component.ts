import {Component, Injectable, Input, OnInit} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {PairService} from "../../../services/pair.service";
import {NotificationService} from "../../../services/notification.service";
import {JournalService} from "../../../services/journal.service";
import {LessonEvent} from "../../../models/journal/lessonEvent.model";
import {PointType} from "../../../models/journal/journal.model";
import {DictionaryService} from "../../../services/dictionary.service";
import {UtilsService} from "../../../services/utils.service";
import {isUndefined} from "util";
import {StatusType} from "../../../models/statusType.model";
import {DatePipe} from "@angular/common";

@Component({
    selector: 'lesson-configurator',
    templateUrl: './lesson-configurator.component.html',
    styleUrls: ['./lesson-configurator.component.css']
})
@Injectable()
export class LessonСonfiguratorComponent implements OnInit {

    @Input() pair: Pair;
    public dates: Array<Date> = new  Array<Date>();
    public events: Array<LessonEvent> = new  Array<LessonEvent>();
    public eventTypes: Array<PointType> = new  Array<PointType>();
    public model: LessonEvent = new LessonEvent();
    public ru;
    public allDisabledDates: Array<Date>;
    public disabledDates: Array<Date>;
    public editMode: boolean = false;
    public datePipe = new DatePipe("ru");

    constructor(private utilsService: UtilsService,
                private pairService: PairService,
                private journalService: JournalService,
                private dictionaryService: DictionaryService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.ru = {
            firstDayOfWeek: 1,
            dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
            dayNamesShort: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб" ],
            dayNamesMin: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб" ],
            monthNames: [ "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ],
            monthNamesShort: [ "Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек" ],
            today: 'Сегодня',
            clear: 'Очистить'
        };
        this.getPointsType();
        this.getEvents();
        this.disableUnusedDays();
        this.model.lesson = this.pair.lesson;
    }

    getEvents() {
        this.journalService.GetEvents(this.pair.lesson.id)
            .subscribe( result => {
                this.events = result.data;
            });
    }

    updateDisabledDates(event) {
        /* Прибавление еденицы к месяцу обаснованно тем,
        * что объект Date() возвращает месяц с нуля, а
        * календарь начиная с нуля.
        */
        let daysInMonth = new Date(event.year, event.month, 0).getDate();
        this.disabledDates = [];
        // Отключаем даты в текущем месяце
        for (let i = 1; i < daysInMonth + 1; i++) {
            let find = this.allDisabledDates.find(function(element) {
                let bool = element.getFullYear() === event.year
                    && (element.getMonth() + 1) === (event.month)
                    && element.getDate() === i;
                return bool;
            });
            if (isUndefined(find))
                this.disabledDates.push(new Date(event.year, event.month - 1, i));
        }
    }

    disableUnusedDays() {
        this.journalService.GetJournalDates(this.pair.lesson.id).subscribe(
            result => {
                this.dates = result.data.dates;
                this.allDisabledDates = [];
                for (let date of this.dates) {
                    let temp = new Date(this.datePipe.transform(date, "yyyy-MM-ddTHH:mm:ss"));
                    this.allDisabledDates.push(temp);
                }
                let date: Date = new Date();
                this.updateDisabledDates({year: date.getFullYear(), month: date.getMonth() + 1});
            }
        );
    }

    getPointsType() {
        this.dictionaryService.GetPointTypes()
            .subscribe( result => {
                this.eventTypes = result.content;
                let deleteItem = this.eventTypes.findIndex(i => i.name === "Посещение");
                this.eventTypes.splice(deleteItem, 1);
                this.model.type = this.eventTypes[0];
            });
    }

    Save() {
        console.log("this.model", this.model);
        this.journalService.SaveEvent(this.model).subscribe(
            result => {
                if (result.status === StatusType.OK.toString()) {
                    this.getEvents();
                    this.CancelEdit();
                }
                this.notificationService.FromStatus(result);
            }
        );
    }

    Remove(id: number) {
        this.journalService.EventDelete(id).subscribe(
            result => {
                if (result.status === StatusType.OK.toString())
                    this.getEvents();
                this.notificationService.FromStatus(result);
            }
        );
    }

    Edit(model: LessonEvent) {
        let tempDate = new Date(this.datePipe.transform(model.date, "yyyy-MM-ddTHH:mm:ss"));
        model.date = tempDate;
        this.editMode = true;
        this.model = model;
    }

    CancelEdit() {
        this.editMode = false;
        this.model = new LessonEvent();
        this.model.lesson = this.pair.lesson;
        this.model.type = this.eventTypes[0];
    }

}






