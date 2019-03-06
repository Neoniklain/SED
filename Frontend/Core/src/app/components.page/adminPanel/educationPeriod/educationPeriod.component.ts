import {Component, OnInit} from "@angular/core";
import {DictionaryService} from "../../../services/dictionary.service";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {Speciality} from "../../../models/shedule/speciality";
import {Institute} from "../../../models/shedule/institute";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {ScheduleService} from "../../../services/schedule.service";
import {EducationPeriod} from "../../../models/educationPeriod";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";

@Component({
    selector: 'education-period',
    templateUrl: "./educationPeriod.component.html",
    styleUrls: ["./educationPeriod.component.css"]
})

export class EducationPeriodComponent implements OnInit {

    public specialities: Array<Speciality>;
    public institutes: Array<Institute>;
    public educationPeriods: Array<EducationPeriod>;
    public startDate: Date;
    public endDate: Date;

    public selectInstitute: Institute = new Institute();
    public selectSpeciality: Speciality = new Speciality();

    public ru: any;

    constructor(private dictionaryService: DictionaryService,
                private notificationService: NotificationService,
                private scheduleService: ScheduleService) {
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
        this.getInstitutes();
    }

    loadSpeciality() {
        this.dictionaryService.Get(Dictionary.specialities).subscribe(
            specialities => {
                this.specialities = specialities.content.filter(x => x.institute.id = this.selectInstitute.id);
            }
        );
    }

    selectLessonForInstitute() {
        this.loadSpeciality();
    }

    getInstitutes() {
        this.dictionaryService.Get(Dictionary.institutes)
            .subscribe((res: PageResult) => {
                this.institutes = res.content;
            });
    }

    getEducationPeriods() {
        this.scheduleService.GetEducationPeriod(this.selectSpeciality.id)
            .subscribe((res) => {
                this.educationPeriods = res.data;
            });
    }

    onClickSpeciality(speciality: Speciality) {
        this.selectSpeciality = speciality;
        this.getEducationPeriods();
    }


    addPeriod() {
        let newEducationPeriod: EducationPeriod = new EducationPeriod();
        newEducationPeriod.startDate = this.startDate;
        newEducationPeriod.endDate = this.endDate;
        newEducationPeriod.speciality = this.selectSpeciality;

        this.scheduleService.SavePeriod(newEducationPeriod)
            .subscribe((res) => {
                this.notificationService.FromStatus(res);
                if (res.status == StatusType[StatusType.OK]) {
                    this.educationPeriods.push(newEducationPeriod);
                }
            });
    }

}
