import {
    Component,
    ElementRef,
    EventEmitter,
    HostListener,
    Input,
    OnChanges,
    OnInit,
    Output,
    ViewChild
} from "@angular/core";
import {Pair} from "../../../models/shedule/pair";
import {Group} from "../../../models/shedule/group";
import {DictionaryService} from "../../../services/dictionary.service";
import {Discipline} from "../../../models/shedule/discipline";
import {Professor} from "../../../models/account/professor";
import {AccountService} from "../../../services/account.service";
import {Room} from "../../../models/shedule/room.model";
import {ScheduleService} from "../../../services/schedule.service";
import {isUndefined} from "util";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";
import {WeekType} from "../../../models/shedule/weekType.enum";
import {PairType} from "../../../models/shedule/pairType";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {User} from "../../../models/account/user.model";
import {Dictionary} from "../../../models/admin/dictionary.model";

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
    @Output() updatePairs = new EventEmitter<any>();

    public pair: Pair = new Pair();

    public findGroups: Array<Group> = new Array<Group>();
    public findDisciplines: Array<Discipline> = new Array<Discipline>();
    public findProfessors: Array<Professor> = new Array<Professor>();
    public findRooms: Array<Room> = new Array<Room>();
    public findPairTypes: Array<PairType> = new Array<PairType>();
    public showDeleteDialog: boolean = false;
    public showIgnoreWarnings: boolean = false;
    public WeekTypes = WeekType;
    public SubGroups = [];
    public curSubGroup;
    public windowXOffset: number;
    public windowYOffset: number;
    public windowWidth: number;
    public windowHeight: number;

    @ViewChild('pairDetails') pairDetailsView: ElementRef;

    constructor(private dictionaryService: DictionaryService,
                private accountService: AccountService,
                private notification: NotificationService,
                private ScheduleService: ScheduleService,
    ) { }

    ngOnInit() {
        this.GetPairTypes();
        this.SubGroups = [
            {
                name: "Первая",
                value: 1
            },
            {
                name: "Вторая",
                value: 2
            }
        ];
        this.windowXOffset = window.pageXOffset;
        this.windowYOffset = window.pageYOffset;
        this.windowWidth = window.innerWidth;
        this.windowHeight = window.innerHeight;
    }

    ngOnChanges() {
        if (!isUndefined(this.editablePair)) {
            this.pair = JSON.parse(JSON.stringify(this.editablePair));
            if (this.pair!=null)
                this.curSubGroup = this.SubGroups.find(x => x.value == this.pair.subgroup);
        }
        else this.pair = null;
        this.showIgnoreWarnings = false;
    }

    @HostListener('window:scroll', ['$event'])
    onChangeOffset() {
        this.windowXOffset = window.pageXOffset;
        this.windowYOffset = window.pageYOffset;
    }

    @HostListener('window:resize', ['$event'])
    onResize() {
        this.windowWidth = window.innerWidth;
        this.windowHeight = window.innerHeight;
    }

    getStyle() {
        let style;
        if (this.pointX && this.pointY) {

            let changedPointY = this.pointY;
            let changedPointX = this.pointX;

            if (!isUndefined(this.pairDetailsView)) {
                let widthDetails = this.pairDetailsView.nativeElement.offsetWidth;
                let heightDetails = this.pairDetailsView.nativeElement.offsetHeight;

                if ( (this.pointY + heightDetails + 5) >  this.windowHeight + this.windowYOffset) {
                    changedPointY = this.pointY - heightDetails;
                }

                if ( (this.pointX + widthDetails + 5) >  this.windowWidth + this.windowXOffset) {
                    changedPointX = this.pointX - widthDetails;
                }
            }

            style = {
                "position": 'fixed',
                "left": changedPointX - this.windowXOffset + 'px',
                "top": changedPointY - this.windowYOffset + 'px'
            };
            return style;
        } else {
            style = {};
            return style;
        }
    }

    AllUpdatePairs(skipWarnings?: boolean) {
        this.checkOnEmpty(true);
        this.ScheduleService.Save(this.pair, skipWarnings).subscribe(
            result => {
                if (result.status === StatusType.OK.toString()) {
                    this.updatePairs.emit();
                    this.closeDetails();
                }
                if (result.status === StatusType.WARNING.toString()) {
                    this.showIgnoreWarnings = true;
                }
                this.notification.FromStatus(result);
            }
        );
    }

    deletePair() {
        this.ScheduleService.Delete(this.pair.id).subscribe(
            result => {
                this.updatePairs.emit();
                this.closeDetails();
                this.notification.FromStatus(result);
            }
        );
    }

    public closeDetails() {
        this.close.emit();
        this.showIgnoreWarnings = false;
    }

    public GetPairTypes() {
        this.dictionaryService.Get(Dictionary.pairTypes)
            .subscribe((res: PageResult) => {
                this.findPairTypes = res.content;
            });
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
                this.pair.id = this.editablePair.id;
            }
        }
        if (this.editablePair.id !== 0 && this.editablePair.weektype ===  week) {
            this.pair.id = this.editablePair.id;
        }
        this.pair.weektype = week;
    }
    public selectPairType(pairType: PairType) {
        this.pair.pairType = pairType;
    }

    public checkOnEmpty(beforeSave?: boolean) {
        if (!beforeSave) this.showIgnoreWarnings = false;
        if (isUndefined(this.pair) || this.pair == null) {
            this.pair = new Pair();
        }
        if (this.pair.lesson.discipline == null) {
            this.pair.lesson.discipline = new Discipline();
        }
        if (this.pair.lesson.professor == null) {
            this.pair.lesson.professor = new Professor();
        }
        if (this.pair.lesson.professor.user == null) {
            this.pair.lesson.professor.user = new User();
        }
        if (this.pair.room == null) {
            this.pair.room = new Room();
        }
        if (this.pair.lesson.group == null) {
            this.pair.lesson.group = new Group();
        }
        if (this.pair.pairType == null || this.pair.pairType.type === "") {
            if (this.findPairTypes)
                this.pair.pairType = this.findPairTypes[0];
            else
                this.pair.pairType = new PairType();
        }
    }

    public searchGroups(event: any) {
        let filter = {
            globalFilter: event.query.substring(0, 60)
        }
        let temp: Array<Group> = new Array();
        this.dictionaryService.Get(Dictionary.groups, filter).subscribe(
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
        this.dictionaryService.Get(Dictionary.disciplines, filter).subscribe(
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
        this.dictionaryService.Get(Dictionary.professors, filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0) {
                    this.findProfessors = temp;
                }
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
        this.dictionaryService.Get(Dictionary.rooms, filter).subscribe(
            result => {
                temp = result.content;
                if (temp.length > 0)
                    this.findRooms = temp;
                else
                    this.findRooms = [];
            }, error => console.error(error)
        );
    }

    changeSubGroup(event) {
        if (this.pair.subgroup == event.value.value) {
            this.curSubGroup = null;
            this.pair.subgroup = 0;
        } else
            this.pair.subgroup = event.value.value;
    }

}