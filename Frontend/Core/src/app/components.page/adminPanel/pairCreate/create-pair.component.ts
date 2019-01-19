import {Component} from "@angular/core";
import {Pair} from "../../../models/shedule/pair";
import {MessageService} from "primeng/components/common/messageservice";
import {ScheduleService} from "../../../services/schedule.service";
import {ActivatedRoute} from "@angular/router";
import {isUndefined} from "util";
import {Professor} from "../../../models/account/professor";
import {Discipline} from "../../../models/shedule/discipline";
import {Room} from "../../../models/shedule/room.model";
import {Group} from "../../../models/shedule/group";
import {DictionaryService} from "../../../services/dictionary.service";
import {AccountService} from "../../../services/account.service";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {NotificationService} from "../../../services/notification.service";
import {DayOfWeek} from "../../../models/shedule/dayOfWeek.enum";
import {WeekType} from "../../../models/shedule/weekType.enum";
import {PairType} from "../../../models/shedule/pairType";
import {Dictionary} from "../../../models/admin/dictionary.model";

@Component({
    selector: 'pair-create',
    templateUrl: './create-pair.component.html',
    styleUrls: ["./create-pair.component.css"]
})

export class PairCreateComponent {
    private id: number;
    public newPair: Pair;
    public profs: Professor[];
    public disciplines: Discipline[];
    public rooms: Room[];
    public groups: Group[];
    public pairTypes: PairType[];
    public weektype;
    public dayofweek;

    constructor(private ScheduleService: ScheduleService,
                private accountService: AccountService,
                private dictionaryService: DictionaryService,
                private activateRoute: ActivatedRoute,
                private notification: NotificationService) {
        this.weektype = WeekType;
        this.dayofweek = DayOfWeek;

        this.newPair = new Pair();
        this.profs = [];
        this.GetProfs();
        this.disciplines = [];
        this.GetDisciplines();
        this.rooms = [];
        this.GetRooms();
        this.groups = [];
        this.GetGroups();
        this.pairTypes = [];
        this.GetPairTypes();
        this.id = activateRoute.snapshot.params['id'];
        if (!isUndefined(this.id)) {
            this.GetPair(this.id);
        }

    }

    public GetPair(id: number) {
        this.ScheduleService.Get(id)
            .subscribe((res) => {
                    this.newPair = res.data;
                });
    }

    public GetProfs() {
        this.accountService.GetProfessors()
            .subscribe((res) => {
                    this.profs = res.data;
                });
    }

    public GetDisciplines() {
        this.dictionaryService.Get(Dictionary.disciplines)
            .subscribe((res: PageResult) => {
                    this.disciplines = res.content;
                });
    }

    public GetRooms() {
        this.dictionaryService.Get(Dictionary.rooms)
            .subscribe((res: PageResult) => {
                    this.rooms = res.content;
                });
    }

    public GetPairTypes() {
        this.dictionaryService.Get(Dictionary.pairTypes)
            .subscribe((res: PageResult) => {
                    this.pairTypes = res.content;
                });
    }

    public GetGroups() {
        this.dictionaryService.Get(Dictionary.groups)
            .subscribe((res: PageResult) => {
                    this.groups = res.content;
                });
    }


    public SavePair() {
        this.ScheduleService.Save(this.newPair).subscribe(
            res => {
                this.notification.FromStatus(res);
            }
        );
    }


}