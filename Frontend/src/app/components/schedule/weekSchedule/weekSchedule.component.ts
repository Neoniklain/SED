import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";

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
    @Input() showDetailOnHover: boolean = false;
    @Input() editable: boolean = false;
    public days = ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"];
    public lessonsTime = [
        {number: 1, Start: "8:30", End: "10:05"},
        {number: 2, Start: "10:15", End: "11:50"},
        {number: 3, Start: "12:15", End: "13:50"},
        {number: 4, Start: "14:00", End: "15:35"},
        {number: 5, Start: "16:00", End: "17:35"},
        {number: 6, Start: "17:45", End: "19:20"}
    ]

    public currentPair: Pair;
    public X: number;
    public Y: number;

    constructor() { }

    ngOnInit(): void { }

    updatePairs() {
        this.updatePair.emit();
    }

    onClick(event, pair) {
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

    onMouseEnter(event: MouseEvent, pair) {
        if (!this.editable && this.showDetailOnHover) {
            this.X = event.layerX;
            this.Y = event.layerY;
            if (this.showDetailOnHover == true)
                this.currentPair = pair;
        }
    }

}
