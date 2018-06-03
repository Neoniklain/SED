import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Pair} from "../../../models/shedule/pair";
import {Group} from "../../../models/shedule/group";
import {DictionaryService} from "../../../services/dictionary.service";
import {Discipline} from "../../../models/shedule/discipline";
import {Professor} from "../../../models/account/professor";
import {AccountService} from "../../../services/accountService";
import {Room} from "../../../models/shedule/room.model";
import {PairService} from "../../../services/pair.service";
import {isUndefined} from "util";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";
import {WeekType} from "../../../models/shedule/weekType.enum";

@Component({
    selector: 'pair-details',
    templateUrl: './pairDeatails.component.html',
    styleUrls: ["./pairDeatails.component.css"]
})

export class PairDetailsComponent implements OnInit, OnChanges {
    @Input() editablePair: Pair;
    @Input() pointX: number;
    @Input() pointY: number;
    @Input() editable: boolean = false;
    @Output() close = new EventEmitter<any>();
    @Output() updatePair = new EventEmitter<any>();

    public findGroups: Array<Group>;
    public findDisciplines: Array<Discipline>;
    public WeekTypes;
    public findProfessors: Array<Professor>;
    public findRooms: Array<Room>;
    public pair: Pair;
    public showDeleteDialog: boolean = false;

    constructor(private dictionaryService: DictionaryService,
                private accountService: AccountService,
                private notification: NotificationService,
                private pairService: PairService,
    ) { }

    ngOnInit() {
        this.WeekTypes = WeekType;
    }

    ngOnChanges() {
        if (!isUndefined(this.editablePair)) this.pair = JSON.parse(JSON.stringify(this.editablePair));
        else this.pair = null;
        console.log("this.pair", this.pair);
    }

    getStyle() {
        let style;
        if (this.pointX && this.pointY) {
            style = {
                "position": 'absolute',
                "left": (this.pointX + 10) + 'px',
                "top": this.pointY + 'px'
            };
            return style;
        } else {
            style = {};
            return style;
        }
    }

    updatePairs() {
        this.pairService.Save(this.pair).subscribe(
            result => {
                if (result.status === StatusType.OK.toString()) {
                    this.updatePair.emit();
                    this.closeDetails();
                }
                this.notification.FromStatus(result);
            }
        );
    }

    deletePair() {
        this.pairService.Delete(this.pair.id).subscribe(
            result => {
                if (result.status === StatusType.OK.toString()) {
                    this.updatePair.emit();
                    this.closeDetails();
                }
                this.notification.FromStatus(result);
            }
        );
    }

    public closeDetails() {
        this.close.emit();
    }

    public selectGroup(group: Group) {
        this.pair.lesson.group = group;
    }
    public selectDiscipline(discipline: Discipline) {
        this.pair.lesson.discipline = discipline;
    }
    public selectProfessor(professor: Professor) {
        this.pair.lesson.professor = professor;
    }
    public selectRoom(room: Room) {
        this.pair.room = room;
    }
    public selectWeekType(week: string) {
        if (this.editablePair.id !== 0 && this.editablePair.weektype !==  week) {
            if (this.editablePair.weektype === WeekType.Все.toString()
            || week === WeekType.Все.toString()) {
                this.pair.id = this.editablePair.id;
            } else {
                this.pair.id = 0;
            }
        }
        if (this.editablePair.id !== 0 && this.editablePair.weektype ===  week) {
            this.pair.id = this.editablePair.id;
        }
        this.pair.weektype = week;
    }

    public checkOneEmpty() {
        if (this.pair.lesson.discipline == null) {
            this.pair.lesson.discipline = new Discipline();
        }
        if (this.pair.lesson.professor == null) {
            this.pair.lesson.professor = new Professor();
        }
        if (this.pair.room == null) {
            this.pair.room = new Room();
        }
        if (this.pair.lesson.group == null) {
            this.pair.lesson.group = new Group();
        }
    }

    public searchGroups(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        }
        let temp: Array<Group> = new Array();
        this.dictionaryService.GetGroups(filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.findGroups = temp;
                else
                    this.findGroups = [];
            }, error => console.error(error)
        );
    }
    public searchDisciplines(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        }
        let temp: Array<Discipline> = new Array();
        this.dictionaryService.GetDisciplines(filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.findDisciplines = temp;
                else
                    this.findDisciplines = [];
            }, error => console.error(error)
        );
    }

    public searchProfessors(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        };
        let temp: Array<Professor> = new Array();
        this.dictionaryService.GetProfessors(filter).subscribe(
            result => {
                temp = result.content;
                console.log("temp", temp);
                if (temp.length > 0)
                    this.findProfessors = temp;
                else
                    this.findProfessors = [];
            }, error => console.error(error)
        );
    }
    public searchRooms(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        };
        let temp: Array<Room> = new Array();
        this.dictionaryService.GetRooms(filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.findRooms = temp;
                else
                    this.findRooms = [];
            }, error => console.error(error)
        );
    }

}