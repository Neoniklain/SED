import {Component} from "@angular/core";
import {Pair} from "../../../models/pair.model";
import {MessageService} from "primeng/components/common/messageservice";
import {PairService} from "../../../services/pair.service";
import {ActivatedRoute} from "@angular/router";
import {isUndefined} from "util";
import {Professor} from "../../../models/professor.model";
import {ProfessorService} from "../../../services/professor.service";
import {Discipline} from "../../../models/discipline.model";
import {Room} from "../../../models/room.model";
import {Group} from "../../../models/group.model";
import {DayOfWeek} from "../../../models/dayofweek.model";
import {DisciplineService} from "../../../services/discipline.service";
import {RoomService} from "../../../services/room.service";
import {GroupService} from "../../../services/group.service";



@Component({
    selector: 'editor-single-pair',
    templateUrl: './editor-single-pair.component.html',
   // styleUrls: [ './editor-single-news.component.css' ]
})

export class EditorSinglePairComponent {
    private id: number;
    public newPair: Pair;
    public profs:Professor[];
    public disciplines:Discipline[];
    public rooms: Room[];
    public groups: Group[];
    public daysofweek:DayOfWeek[];


    constructor(private pairService: PairService,
                private profsService:ProfessorService,
                private discipService: DisciplineService,
                private roomService: RoomService,
                private groupService: GroupService,
                private activateRoute: ActivatedRoute,
                private messageService: MessageService) {

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
            .subscribe((res: any) => {
                    this.newPair = res;
                    console.log("Пара получена");
                },
                (error: any) => {
                    console.error('Error: ' + error);
                });
    }

    public GetProfs() {
        this.profsService.GetAll()
            .subscribe((res: any) => {
                    this.profs = res;
                },
                error => console.error(error));
    }

    public GetDisciplines() {
        this.discipService.GetAll()
            .subscribe((res: any) => {
                    this.disciplines = res;
                },
                error => console.error(error));
    }

    public GetRooms() {
        this.roomService.GetAll()
            .subscribe((res: any) => {
                    this.rooms = res;
                },
                error => console.error(error));
    }

    public GetGroups() {
        this.groupService.GetAll()
            .subscribe((res: any) => {
                    this.groups = res;
                },
                error => console.error(error));
    }


    public SavePair() {
        this.pairService.Save(this.newPair).subscribe(
            res => {
                this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Пара успешно создана.'});
            },
            error => console.error(error),
        );
    }


}