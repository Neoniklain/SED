import {Component} from "@angular/core";
import {Pair} from "../../../models/pair.model";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {GroupService} from "../../../services/group.service";

@Component({
    selector: 'list-pairs-group',
    templateUrl: './list-pairs-group.component.html',
   // styleUrls: ['./list-pairs-group.component.css']
})

export class ListPairsGroupComponent {

    public chetPairs: Pair[];
    public FirstChetPairs: Pair[];
    public SecondChetPairs: Pair[];
    public ThirdChetPairs: Pair[];
    public FourthChetPairs: Pair[];
    public FifthChetPairs: Pair[];

    public nechetPairs: Pair[];
    public FirstNechetPairs: Pair[];
    public SecondNechetPairs: Pair[];
    public ThirdNechetPairs: Pair[];
    public FourthNechetPairs: Pair[];
    public FifthNechetPairs: Pair[];

    private idGroup: number;

    constructor(private activateRoute: ActivatedRoute, private groupService: GroupService){
        this.chetPairs=[];
        this.FirstChetPairs=[];
        this.SecondChetPairs=[];
        this.ThirdChetPairs=[];
        this.FourthChetPairs=[];
        this.FifthChetPairs=[];

        this.nechetPairs=[];
        this.FirstNechetPairs=[];
        this.SecondNechetPairs=[];
        this.ThirdNechetPairs=[];
        this.FourthNechetPairs=[];
        this.FifthNechetPairs=[];

        this.idGroup = activateRoute.snapshot.params['id'];
        this.GetPairs(this.idGroup);
    }

    public GetPairs(idGroup:number){
        this.groupService.GetChetPairs(idGroup)
            .subscribe((res: any) => {
                    this.chetPairs= res;
                    this.sortChetPairs(this.chetPairs);
                },
                error => console.error(error))


        this.groupService.GetNechetPairs(idGroup)
            .subscribe((res: any) => {
                    this.nechetPairs= res;
                    this.sortNechetPairs(this.nechetPairs);
                },
                error => console.error(error))
    }

    public sortChetPairs(listPairs:Pair[]){
        for (var i=0; i < listPairs.length; i++){
            if (listPairs[i].pairnumber==1){
                this.placePair(listPairs[i],this.FirstChetPairs)
            }
            if (listPairs[i].pairnumber==2){
                this.placePair(listPairs[i],this.SecondChetPairs)
            }
            if (listPairs[i].pairnumber==3){
                this.placePair(listPairs[i],this.ThirdChetPairs)
            }
            if (listPairs[i].pairnumber==4){
                this.placePair(listPairs[i],this.FourthChetPairs)
            }
            if (listPairs[i].pairnumber==5){
                this.placePair(listPairs[i],this.FifthChetPairs)
            }
        }
    }

    public sortNechetPairs(listPairs:Pair[]){
        for (var i=0; i < listPairs.length; i++){
            if (listPairs[i].pairnumber==1){
                this.placePair(listPairs[i],this.FirstNechetPairs)
            }
            if (listPairs[i].pairnumber==2){
                this.placePair(listPairs[i],this.SecondNechetPairs)
            }
            if (listPairs[i].pairnumber==3){
                this.placePair(listPairs[i],this.ThirdNechetPairs)
            }
            if (listPairs[i].pairnumber==4){
                this.placePair(listPairs[i],this.FourthNechetPairs)
            }
            if (listPairs[i].pairnumber==5){
                this.placePair(listPairs[i],this.FifthNechetPairs)
            }
        }
    }

    public placePair (pair:Pair, listToPlacePair:Pair[]) {
        switch (pair.dayofweek) {
            case "Понедельник":
                listToPlacePair.splice(0, 1, pair);
                break;
            case "Вторник":
                listToPlacePair.splice(1, 1, pair);
                break;
            case "Среда":
                listToPlacePair.splice(2, 1, pair);
                break;
            case "Четверг":
                listToPlacePair.splice(3, 1, pair);
                break;
            case "Пятница":
                listToPlacePair.splice(4, 1, pair);
                break;
            case "Суббота":
                listToPlacePair.splice(5, 1, pair);
                break;
            default:
                break;
        }
    }

}
