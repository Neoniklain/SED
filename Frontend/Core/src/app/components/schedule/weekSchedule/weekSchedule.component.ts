import {
    Component,
    EventEmitter,
    Injectable,
    Input,
    OnChanges,
    OnInit,
    Output,
    Renderer2,
    SimpleChanges
} from '@angular/core';
import {Pair, ScheduleShowedPairs} from "../../../models/shedule/pair";
import {PairNumber} from "../../../models/shedule/pairNumber.model";
import {DayOfWeek} from "../../../models/shedule/dayOfWeek.enum";
import {WeekType} from "../../../models/shedule/weekType.enum";
import {ScheduleService} from "../../../services/schedule.service";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";
import {SelectItem} from "primeng/api";
import {Group} from "../../../models/shedule/group";
import {SemesterNumberYear} from "../../../models/semesterNumberYear.model";

@Component({
    selector: 'schedule-week',
    templateUrl: './weekSchedule.component.html',
    styleUrls: ['./weekSchedule.component.css']
})
@Injectable()
export class WeekScheduleComponent implements OnInit, OnChanges {

    @Output() clickPair = new EventEmitter<Pair>();
    @Output() updatePair = new EventEmitter<any>();
    @Input() pairs: Array<Pair>;
    @Input() templatePair: Pair = new Pair();
    @Input() showDetailOnHover: boolean = false;
    @Input() editable: boolean = false;
    @Input() semesterNumberYear: SemesterNumberYear = new SemesterNumberYear();

    // Только для отображения
    public showedPairs: Array<ScheduleShowedPairs> = [];
    public cleckedPair: ScheduleShowedPairs = null;

    public days = Object.keys(DayOfWeek);
    public lessonsTime = Array<PairNumber>();

    public currentPair: Pair;
    public draggedPair: Pair;
    public movedPair: Pair;
    public showDeleteDialog: boolean = false;
    public showGroupChooseDialog: boolean = false;
    public choosedGroups: Array<Group> = [];
    public X: number;
    public Y: number;

    public types: SelectItem[];
    public selectedType: string;
    public prityWeekNum: number;


    constructor(
        private notification: NotificationService,
        private renderer: Renderer2,
        private scheduleService: ScheduleService) {
    }

    ngOnInit(): void {
        this.lessonsTime.push(new PairNumber(1));
        this.lessonsTime.push(new PairNumber(2));
        this.lessonsTime.push(new PairNumber(3));
        this.lessonsTime.push(new PairNumber(4));
        this.lessonsTime.push(new PairNumber(5));
        this.lessonsTime.push(new PairNumber(6));
        this.lessonsTime.push(new PairNumber(7));
        if (!this.templatePair) this.templatePair = new Pair();
        this.types = [
            {label: 'Все', value: 'Все'},
            {label: 'Нечетные', value: 'Нечет'},
            {label: 'Четные', value: 'Чет'}
        ];
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.prityWeek().subscribe(
            result => {
                this.prityWeekNum = result;
                this.selectedType = result == 0 ? 'Нечет' : 'Чет';
            }, error1 => {
                this.selectedType = 'Все';
            }
        );
        // Удаляем отображение субботы если в текущем расписанаа нет пар в субботу
        let findSundayPair = this.pairs.find(x => x.dayofweek == "Суббота");
        if (!findSundayPair && this.days.indexOf("Суббота") != -1) {
            this.days.splice(this.days.indexOf("Суббота"), 1);
        }

        if (!this.editable) {
            let temPairs: Array<Pair> = JSON.parse(JSON.stringify(this.pairs));
            for (let i = 0; i < temPairs.length; i++) {
                let p = temPairs[i];
                if (!p) continue;
                let findPair: Pair = temPairs.find(o => o.dayofweek == p.dayofweek
                    && o.pairNumber == p.pairNumber
                    && o.weektype == p.weektype
                    && o.lesson.professor.id == p.lesson.professor.id
                    && o.lesson.discipline.id == p.lesson.discipline.id
                    && o.lesson.group.id != p.lesson.group.id
                    && o.id != p.id);
                let newShoedPair = new ScheduleShowedPairs(p);

                if (findPair) {
                    newShoedPair = new ScheduleShowedPairs(p);
                    newShoedPair.lesson.groups.push(findPair.lesson.group);
                    temPairs.splice(temPairs.map(x => x.id).indexOf(findPair.id), 1);
                }
                this.showedPairs.push(newShoedPair);
            }
        }
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

    onClickPair(pair: ScheduleShowedPairs) {
        if (pair.lesson.groups.length > 1) {
            this.cleckedPair = pair;
            this.showGroupChooseDialog = true;
            this.choosedGroups = pair.lesson.groups;
        } else {
            this.clickPair.emit(this.pairs.find(x => x.id == pair.id));
            this.showGroupChooseDialog = false;
        }
    }

    selectGroup(group: Group) {
        let findPair = this.pairs.find(
            o => o.dayofweek == this.cleckedPair.dayofweek
                && o.pairNumber == this.cleckedPair.pairNumber
                && o.weektype == this.cleckedPair.weektype
                && o.lesson.professor.id == this.cleckedPair.lesson.professor.id
                && o.lesson.discipline.id == this.cleckedPair.lesson.discipline.id
                && o.lesson.group.id == group.id);
        this.clickPair.emit(findPair);

        this.cleckedPair = null;
        this.showGroupChooseDialog = false;
        this.choosedGroups = [];
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

    existPairInThisDay(p: ScheduleShowedPairs) {
        let findPair = this.showedPairs.find(o => o.dayofweek == p.dayofweek
            && o.pairNumber == p.pairNumber
            && (o.weektype == this.selectedType || this.selectedType == 'Все' || o.weektype == 'Все')
            && o.id != p.id);
        return findPair;
    }

    prityWeek() {
        return this.scheduleService.GetPrityWeek(this.semesterNumberYear);
    }

}
