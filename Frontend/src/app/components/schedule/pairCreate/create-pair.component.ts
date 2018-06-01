import {Component} from "@angular/core";
import {Pair} from "../../../models/shedule/pair";
import {MessageService} from "primeng/components/common/messageservice";
import {PairService} from "../../../services/pair.service";
import {ActivatedRoute} from "@angular/router";
import {isUndefined} from "util";
import {Professor} from "../../../models/account/professor";
import {Discipline} from "../../../models/shedule/discipline";
import {Room} from "../../../models/shedule/room.model";
import {Group} from "../../../models/shedule/group";
import {DictionaryService} from "../../../services/dictionary.service";
import {AccountService} from "../../../services/accountService";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {WeekType} from "../../../models/shedule/weekType.model";
import {DayOfWeek} from "../../../models/shedule/dayOfWeek.model";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";

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
    public weektype;
    public dayofweek;

    constructor(private pairService: PairService,
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
        this.id = activateRoute.snapshot.params['id'];
        if (!isUndefined(this.id)) {
            this.GetPair(this.id);
        }

    }

    public GetPair(id: number) {
        this.pairService.Get(id)
            .subscribe((res) => {
                    this.newPair = res.data;
                });
    }

    public GetProfs() {
        this.accountService.GetProfessors()
            .subscribe((res) => {
                    console.log("res", res);
                    this.profs = res.data;
                });
    }

    public GetDisciplines() {
        this.dictionaryService.GetDisciplines()
            .subscribe((res: PageResult) => {
                    this.disciplines = res.content;
                });
    }

    public GetRooms() {
        this.dictionaryService.GetRooms()
            .subscribe((res: PageResult) => {
                    this.rooms = res.content;
                });
    }

    public GetGroups() {
        this.dictionaryService.GetGroups()
            .subscribe((res: PageResult) => {
                    this.groups = res.content;
                });
    }


    public SavePair() {
        this.pairService.Save(this.newPair).subscribe(
            res => {
                this.notification.FromStatus(res);
            }
        );
    }


}