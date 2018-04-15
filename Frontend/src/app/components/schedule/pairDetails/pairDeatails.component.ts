import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Pair} from "../../../models/shedule/pair";
import {Group} from "../../../models/group";
import {DictionaryService} from "../../../services/dictionary.service";
import {Discipline} from "../../../models/discipline";
import {Professor} from "../../../models/professor";
import {AccountService} from "../../../services/accountService";
import {Room} from "../../../models/room.model";
import {PairService} from "../../../services/pair.service";
import {ToastrService} from "ngx-toastr";
import {isUndefined} from "util";

@Component({
    selector: 'pair-details',
    templateUrl: './pairDeatails.component.html',
    styleUrls: ["./pairDeatails.component.css"]
})

export class PairDetailsComponent implements OnInit, OnChanges {
    @Input() editablePair: Pair = null;
    @Input() pointX: number;
    @Input() pointY: number;
    @Input() editable: boolean = false;
    @Output() close = new EventEmitter<any>();
    @Output() updatePair = new EventEmitter<any>();

    public findGroups: Array<Group>;
    public findDisciplines: Array<Discipline>;
    public findWeektypes: Array<String>;
    public findProfessors: Array<Professor>;
    public findRooms: Array<Room>;
    public pair: Pair;
    public showDeleteDialog: boolean = false;

    constructor(private dictionaryService: DictionaryService,
                private accountService: AccountService,
                private toastr: ToastrService,
                private pairService: PairService,
    ) { }

    ngOnInit() { }

    ngOnChanges() {
        if (!isUndefined(this.editablePair)) this.pair = JSON.parse(JSON.stringify(this.editablePair));
        else this.pair = null;
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
                if (result.status === "ok") {
                    this.updatePair.emit();
                    this.closeDetails();
                    this.toastr.success("Занятие успешно обновлено", "Успешно!");
                } else {
                    this.toastr.error("Занятие не обновлено", "Ошибка.");
                }
            }, error => {
                this.toastr.error("Занятие не обновлено", "Ошибка.");
            }
        );
    }

    deletePair() {
        this.pairService.Delete(this.pair.id).subscribe(
            result => {
                if (result.status === "ok") {
                    this.updatePair.emit();
                    this.closeDetails();
                    this.toastr.success("Занятие успешно удалено", "Успешно!");
                } else {
                    this.toastr.error("Занятие не удалено", "Ошибка.");
                }
            }, error => {
                this.toastr.error("Занятие не удалено", "Ошибка.");
            }
        );
    }

    public closeDetails() {
        this.close.emit();
    }

    public selectGroup(group: Group) {
        this.pair.group = group;
    }
    public selectDiscipline(discipline: Discipline) {
        this.pair.discipline = discipline;
    }
    public selectProfessor(professor: Professor) {
        this.pair.professor = professor;
    }
    public selectRoom(room: Room) {
        this.pair.room = room;
    }

    public checkOneEmpty() {
        if (this.pair.discipline == null) {
            this.pair.discipline = new Discipline();
        }
        if (this.pair.professor == null) {
            this.pair.professor = new Professor();
        }
        if (this.pair.room == null) {
            this.pair.room = new Room();
        }
        if (this.pair.group == null) {
            this.pair.group = new Group();
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
    public searchWeektypes(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        };
        let temp: Array<String> = new Array();
        this.dictionaryService.GetWeekTypes(filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.findWeektypes = temp;
                else
                    this.findWeektypes = [];
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