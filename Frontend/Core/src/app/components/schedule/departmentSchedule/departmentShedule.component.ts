import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {DepartmentShedule} from "../../../models/shedule/departmentShedule";
import {Pair} from "../../../models/shedule/pair";
import {SemesterNumberYear} from "../../../models/semesterNumberYear.model";

@Component({
    selector: 'schedule-department',
    templateUrl: './departmentShedule.component.html',
    styleUrls: ['./departmentShedule.component.css']
})
@Injectable()
export class DepartmentScheduleComponent implements OnInit {

    @Output() clickPair = new EventEmitter<any>();
    @Input() schedule: Array<DepartmentShedule>;
    @Input() showDetailOnHover: boolean = true;
    @Input() semesterNumberYear: SemesterNumberYear;

    public days = ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"];

    public pairNumbers = [1, 2, 3, 4, 5, 6];
    public DayPairNumber;
    public currentPair: Pair;
    public X: number;
    public Y: number;

    constructor() { }

    ngOnInit(): void {
        this.DayPairNumber = [];
        for (let dayofweek of this.days) {
            for (let pairNumber of this.pairNumbers) {
                this.DayPairNumber.push({dayofweek: dayofweek, pairNumber: pairNumber});
            }
        }
    }

    onClick(pair) {
        this.clickPair.emit(pair);
    }

    onMouseLeave() {
        this.currentPair = null;
    }

    onMouseMove(event: MouseEvent) {
        this.X = event.layerX;
        this.Y = event.layerY - 20;
    }

    onMouseEnter(event: MouseEvent, pair) {
        this.X = event.layerX;
        this.Y = event.layerY - 20;
        if (this.showDetailOnHover == true)
            this.currentPair = pair;
    }

}
