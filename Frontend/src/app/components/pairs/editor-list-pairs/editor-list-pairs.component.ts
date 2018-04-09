import {Component} from "@angular/core";
import {Pair} from "../../../models/pair.model";
import {ActivatedRoute, Router} from "@angular/router";
import {ProfessorService} from "../../../services/professor.service";
import {RouteConstants} from "../../../bootstrap/app.route.constants";
import {MessageService} from "primeng/components/common/messageservice";
import {ConfirmationService} from "primeng/components/common/confirmationservice";
import {PairService} from "../../../services/pair.service";



@Component({
    selector: 'editor-list-pairs',
    templateUrl: './editor-list-pairs.component.html',
    // styleUrls: [ './editor-single-news.component.css' ]
})

export class EditorListPairsComponent {
    private idProf: number;
    public chetPairs: Pair[];
    public nechetPairs: Pair[];


    constructor(private router: Router,
                private pairService: PairService,
                private profService: ProfessorService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService,
                private activateRoute: ActivatedRoute) {

        this.chetPairs=[];
        this.nechetPairs=[];
        this.idProf = activateRoute.snapshot.params['id'];
        this.GetPairs(this.idProf);
    }

    public GetPairs(idProf:number){
        this.profService.GetChetPairs(idProf)
            .subscribe((res: any) => {
                    this.chetPairs= res;
                },
                error => console.error(error))


        this.profService.GetNechetPairs(idProf)
            .subscribe((res: any) => {
                    this.nechetPairs= res;
                },
                error => console.error(error))
    }

    public DeleteChet(id: number) {
        this.confirmationService.confirm({
            message: 'Вы действительно хотите удалить пару?',
            header: 'Удалить пару?',
            icon: 'fa fa-trash',
            accept: () => {
                this.pairService.Delete(id).subscribe(
                    (res: any) => {
                        for (let pair of this.chetPairs) {
                            if (pair.id == id)
                            {
                                this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Пара успешно удалена.'});
                                this.chetPairs.splice(this.chetPairs.indexOf(pair), 1);
                            }
                        }
                    },
                    (error: any) => { console.error('Error: ' + error); });
            }
        });

    }

    public DeleteNehet(id: number) {
        this.confirmationService.confirm({
            message: 'Вы действительно хотите удалить пару?',
            header: 'Удалить пару?',
            icon: 'fa fa-trash',
            accept: () => {
                this.pairService.Delete(id).subscribe(
                    (res: any) => {
                        for (let pair of this.nechetPairs) {
                            if (pair.id == id)
                            {
                                this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Пара успешно удалена.'});
                                this.nechetPairs.splice(this.nechetPairs.indexOf(pair), 1);
                            }
                        }
                    },
                    (error: any) => { console.error('Error: ' + error); });
            }
        });

    }


    public gotoEdit(idPair: number) {
        this.router.navigateByUrl(RouteConstants.Pair.Edit.replace(":id", idPair.toString()));
    }

}