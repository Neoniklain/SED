import {Component} from "@angular/core";
import {Pair} from "../../../models/shedule/pair";
import {MessageService} from "primeng/components/common/messageservice";
import {PairService} from "../../../services/pair.service";
import {ActivatedRoute} from "@angular/router";
import {isUndefined} from "util";
import {Professor} from "../../../models/professor";
import {Discipline} from "../../../models/discipline";
import {Room} from "../../../models/room.model";
import {Group} from "../../../models/group";
import {DictionaryService} from "../../../services/dictionary.service";
import {AccountService} from "../../../services/accountService";
import {PageResult} from "../../../models/admin/PageResult.model.list";

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
    public weektypes: string[];
    public dayofweeks: string[];

    constructor(private pairService: PairService,
                private accountService: AccountService,
                private dictionaryService: DictionaryService,
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
        this.dictionaryService.GetWeekTypes().subscribe(
            result => {
                console.log("result", result);
                this.weektypes = result.content;
            }
        );
        this.dictionaryService.GetDayOfWeeks().subscribe(
            result => {
                console.log("result", result);
                this.dayofweeks = result.content;
            }
        );
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
        this.accountService.GetProfessors()
            .subscribe((res: any) => {
                    this.profs = res;
                },
                error => console.error(error));
    }

    public GetDisciplines() {
        this.dictionaryService.GetDisciplines()
            .subscribe((res: PageResult) => {
                    this.disciplines = res.content;
                },
                error => console.error(error));
    }

    public GetRooms() {
        this.dictionaryService.GetRooms()
            .subscribe((res: PageResult) => {
                    this.rooms = res.content;
                },
                error => console.error(error));
    }

    public GetGroups() {
        this.dictionaryService.GetGroups()
            .subscribe((res: PageResult) => {
                    this.groups = res.content;
                },
                error => console.error(error));
    }


    public SavePair() {
        this.pairService.Save(this.newPair).subscribe(
            res => {
                if (res.status == "ok")
                    this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Пара успешно создана.'});
                else {
                    let errorMessage = 'Пара не была создана.';
                    if (res.message !== "")
                        errorMessage = res.message;
                    this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: errorMessage});
                }
            },
            error => {
                this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Пара не была создана.'});
            }
        );
    }


}