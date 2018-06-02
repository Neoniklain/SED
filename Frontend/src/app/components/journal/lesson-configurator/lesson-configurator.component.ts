import {Component, Injectable, Input, OnInit} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {AuthenticationService} from "../../../services/authService";
import {PairService} from "../../../services/pair.service";
import {NotificationService} from "../../../services/notification.service";
import {JournalService} from "../../../services/journal.service";
import {LessonEvent} from "../../../models/journal/lessonEvent.model";
import {PointType} from "../../../models/journal/journal.model";
import {DictionaryService} from "../../../services/dictionary.service";

@Component({
    selector: 'lesson-configurator',
    templateUrl: './lesson-configurator.component.html',
    styleUrls: ['./lesson-configurator.component.css']
})
@Injectable()
export class LessonСonfiguratorComponent implements OnInit {

    @Input() pair: Pair;
    public events: Array<LessonEvent> = new  Array<LessonEvent>();
    public eventTypes: Array<PointType> = new  Array<PointType>();
    public model: LessonEvent = new LessonEvent();
    public ru;
    public disabledDays: Array<number>;

    constructor(private authenticationService: AuthenticationService,
                private pairService: PairService,
                private journalService: JournalService,
                private dictionaryService: DictionaryService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.ru = {
            firstDayOfWeek: 0,
            dayNames: ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"],
            dayNamesShort: ["Пн", "Вт", "СР", "Чт", "Пт", "Сб", "Вск"],
            dayNamesMin: ["Пн", "Вт", "СР", "Чт", "Пт", "Сб", "Вск"],
            monthNames: [ "Январь", "Февраль", "Март", "Апрель", "Ми ", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ],
            monthNamesShort: [ "Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек" ],
            today: 'Сегодня',
            clear: 'Очистить'
        };
        this.getPointsType();
        this.getEvents();
        this.model.lesson = this.pair.lesson;
        this.disabledDays = [1, 2];
    }

    getEvents() {
        this.journalService.GetEvents(this.pair.lesson.id)
            .subscribe( result => {
                this.events = result.data;
            });
    }

    getPointsType() {
        this.dictionaryService.GetPointTypes()
            .subscribe( result => {
                this.eventTypes = result.content;
            });
    }

    test() {
        console.log("this.model", this.model);
    }

}






