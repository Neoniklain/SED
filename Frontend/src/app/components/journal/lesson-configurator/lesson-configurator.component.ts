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
    public pairs: Array<Pair> = new  Array<Pair>();
    public events: Array<LessonEvent> = new  Array<LessonEvent>();
    public eventTypes: Array<PointType> = new  Array<PointType>();
    public model: LessonEvent = new LessonEvent();
    public ru;
    public disabledDays: Array<number>;
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
            monthNames: [ "Январь", "Февраль", "Март", "Апрель", "Ми ", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ],
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

    disableUnusedDays() {
        this.pairService.GetLessonPair(this.pair.lesson.id).subscribe(
            result => {
                this.pairs = result.data;
                let enabledDays = [];
                for (let pair of this.pairs) {
                    enabledDays.push(this.utilsService.getDayNumFromName(pair.dayofweek));
                }
                this.disabledDays = [];
                for (let i = 0; i < 7; i++) {
                    if (isUndefined(enabledDays.find(function(element) { return element === i; }))) {
                        if (this.disabledDays.length === 0
                            || isUndefined(this.disabledDays.find(function(element) { return element === i; }))) {
                            this.disabledDays.push(i);
                        }
                    }
                }

            }
        );
    }

    getPointsType() {
        this.dictionaryService.GetPointTypes()
            .subscribe( result => {
                this.eventTypes = result.content;
                let deleteItem = this.eventTypes.findIndex(i => i.name === "Посещение");
                this.eventTypes.splice(deleteItem, 1);

            });
    }

    Save() {
        console.log("saved model:", this.model);
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
    }

}






