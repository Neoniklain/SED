import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {PairNumber, PairTime} from "../../../models/shedule/pairNumber.model";
import {DayOfWeek} from "../../../models/shedule/dayOfWeek.enum";
import {PairType} from "../../../models/shedule/pairType";

@Component({
    selector: 'schedule-week',
    templateUrl: './weekSchedule.component.html',
    styleUrls: ['./weekSchedule.component.css']
})
@Injectable()
export class WeekScheduleComponent implements OnInit {

    @Output() clickPair = new EventEmitter<Pair>();
    @Output() updatePair = new EventEmitter<any>();
    @Input() pairs: Array<Pair>;
    @Input() templatePair: Pair;
    @Input() showDetailOnHover: boolean = false;
    @Input() editable: boolean = false;

    public days = Object.keys(DayOfWeek);
    public lessonsTime = Array<PairNumber>();

    public currentPair: Pair;
    public X: number;
    public Y: number;

    constructor() { }

    ngOnInit(): void {
        this.lessonsTime.push(new PairNumber(1));
        this.lessonsTime.push(new PairNumber(2));
        this.lessonsTime.push(new PairNumber(3));
        this.lessonsTime.push(new PairNumber(4));
        this.lessonsTime.push(new PairNumber(5));
        this.lessonsTime.push(new PairNumber(6));
        if (!this.templatePair) this.templatePair = new Pair();
    }

    updatePairs() {
        this.updatePair.emit();
    }

    onClick(event, day, lessonTime, weektype?) {
        let pair = this.templatePair;
        pair.dayofweek = day;
        pair.pairNumber = lessonTime;
        pair.weektype = weektype;
        for (let p of this.pairs) {
            if (p.dayofweek === day && p.pairNumber === lessonTime
                && (p.weektype === weektype || !weektype))
                pair = p;
        }
        this.clickPair.emit(pair);
        if (this.editable) {
            this.X = event.layerX;
            this.Y = event.layerY;
            this.currentPair = pair;
        }
    }

    closeDetails() {
        if (this.editable) {
            this.currentPair = null;
        }
    }

    onMouseLeave() {
        if (!this.editable && this.showDetailOnHover) {
            this.currentPair = null;
        }
    }

    onMouseMove(event: MouseEvent) {
        if (!this.editable && this.showDetailOnHover) {
            this.X = event.layerX;
            this.Y = event.layerY;
        }
    }

    onMouseEnter(event: MouseEvent, day, lessonTime: PairNumber, weektype?) {
        let pair = this.templatePair;
        pair.dayofweek = day;
        pair.pairNumber = lessonTime.num;
        pair.weektype = weektype;
        for (let p of this.pairs) {
            if (p.dayofweek === day && p.pairNumber === lessonTime.num
                && (p.weektype === weektype || !weektype))
                pair = p;
        }
        if (!this.editable && this.showDetailOnHover) {
            this.X = event.layerX;
            this.Y = event.layerY;
            if (this.showDetailOnHover == true)
                this.currentPair = pair;
        }
    }

}
