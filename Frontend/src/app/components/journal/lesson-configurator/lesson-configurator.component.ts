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

    constructor(private authenticationService: AuthenticationService,
                private pairService: PairService,
                private journalService: JournalService,
                private dictionaryService: DictionaryService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {
        this.ru = {
            firstDayOfWeek: 0,
            dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
            dayNamesShort: ["Вск", "Пн", "Вт", "СР", "Чт", "Пт", "Сб"],
            dayNamesMin: ["Вск","Пн","Вт","СР","Чт","Пт","Сб"],
            monthNames: [ "Январь","Февраль","Март","Апрель","Ми ","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь" ],
            monthNamesShort: [ "Янв", "Фев", "Мар", "Апр", "Май", "Июн","Июл", "Авг", "Сен", "Окт", "Ноя", "Дек" ],
            today: 'Сегодня',
            clear: 'Очистить'
        };
        this.getPointsType();
        this.getEvents();
        this.model.lesson.professor = this.pair.professor;
        this.model.lesson.group = this.pair.group;
        this.model.lesson.discipline = this.pair.discipline;
    }

    getEvents() {
        this.journalService.GetEvents(this.pair.professor.id, this.pair.group.id, this.pair.discipline.id)
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






