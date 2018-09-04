import {Component, EventEmitter, Injectable, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {PairNumber, PairTime} from "../../../models/shedule/pairNumber.model";
import {DayOfWeek} from "../../../models/shedule/dayOfWeek.enum";
import {PairType} from "../../../models/shedule/pairType";
import {WeekType} from "../../../models/shedule/weekType.enum";
import {isUndefined} from "util";
import {ScheduleService} from "../../../services/schedule.service";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";

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
    @Input() templatePair: Pair = new Pair();
    @Input() showDetailOnHover: boolean = false;
    @Input() editable: boolean = false;

    public days = Object.keys(DayOfWeek);
    public lessonsTime = Array<PairNumber>();

    public currentPair: Pair;
    public draggedPair: Pair;
    public movedPair: Pair;
    public showDeleteDialog: boolean = false;
    public X: number;
    public Y: number;

    constructor(
        private notification: NotificationService,
        private scheduleService: ScheduleService) { }

    ngOnInit(): void {
        this.lessonsTime.push(new PairNumber(1));
        this.lessonsTime.push(new PairNumber(2));
        this.lessonsTime.push(new PairNumber(3));
        this.lessonsTime.push(new PairNumber(4));
        this.lessonsTime.push(new PairNumber(5));
        this.lessonsTime.push(new PairNumber(6));
        this.lessonsTime.push(new PairNumber(7));
        if (!this.templatePair) this.templatePair = new Pair();

        // TODO: Возможно в будущем придется объединять потоковые занятия.
        /* if (!this.editable) {
            for (let p of this.pairs) {

                let findPair = this.pairs.find(o => o.dayofweek == p.dayofweek
                && o.pairNumber == p.pairNumber
                && o.weektype == p.weektype
                && o.lesson.professor.id == p.lesson.professor.id
                && o.lesson.discipline.id == p.lesson.discipline.id
                && o.lesson.group.id != p.lesson.group.id
                && o.id != p.id);

                console.log("findPair", findPair)

            }
        }*/
    }

    updatePairs() {
        this.updatePair.emit();
    }

    onClick(event, day, lessonTime, weektype?) {
        let pair = this.templatePair;
        pair.dayofweek = day;
        pair.pairNumber = lessonTime;
        if (!weektype)
            pair.weektype = WeekType.Все.toString();
        else
            pair.weektype = weektype;
        for (let p of this.pairs) {
            if (p.dayofweek === day && p.pairNumber === lessonTime
                && p.weektype === weektype) {
                pair = p;
            }
        }
        this.clickPair.emit(pair);
        if (this.editable) {
            this.X = event.pageX;
            this.Y = event.pageY;
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
            this.X = event.pageX;
            this.Y = event.pageY;
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
            this.X = event.pageX;
            this.Y = event.pageY;
            if (this.showDetailOnHover == true)
                this.currentPair = pair;
        }
    }

    movePair() {
        this.scheduleService.Save(this.movedPair).subscribe(
            result => {
                if (result.status == StatusType.OK.toString()) {
                    this.movedPair = null;
                    this.updatePairs();
                }
                this.showDeleteDialog = false;
                this.notification.FromStatus(result);
            }
        );
    }

    dragStart(event: DragEvent, pair: Pair) {
        this.draggedPair = pair;
    }

    dragEnd(event: DragEvent) {
        this.draggedPair = null;
    }

    drop(event: DragEvent, lessonTime: PairNumber, day: string) {
        let element = this.getTDParent(event.toElement)
        if (element.nodeName == 'TD') {
            element.className = element.className.replace("dropped-element", "");
        }
        if (this.draggedPair.pairNumber != lessonTime.num || this.draggedPair.dayofweek != day) {
            this.movedPair = JSON.parse(JSON.stringify(this.draggedPair));
            this.movedPair.pairNumber = lessonTime.num;
            this.movedPair.dayofweek = day;
            this.showDeleteDialog = true;
        }
    }

    dragEnter(event: DragEvent) {
        let element = this.getTDParent(event.toElement)
        if (element != null) {
            element.className = element.className + " dropped-element";
        }
    }
    dragLeave(event: DragEvent) {
        let element = this.getTDParent(event.toElement)
        if (element != null) {
            element.className = element.className.replace("dropped-element", "");
        }
    }

    getTDParent(element: Element) {
        if (element.nodeName == 'TD') {
            return element;
        } else if (element.parentElement.nodeName == 'BODY') {
            return null;
        } else {
            return this.getTDParent(element.parentElement);
        }
    }

}
