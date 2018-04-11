
import {Component} from "@angular/core";
import {Pair} from "../../../models/pair.model";
import {DepartmentService} from "../../../services/department.service";
import {DepartmentPair} from "../../../models/departmentPair.model";

@Component({
    selector: 'list-pairs-department',
    templateUrl: './list-pairs-department.component.html',
    //styleUrls: ['./list-pairs-professor.component.css']
})

export class ListPairsDepartmentComponent{

    public chetDepartPairs: DepartmentPair[];
    public nechetDepartPairs:  DepartmentPair[];


    constructor(private departmentService: DepartmentService){
        this.chetDepartPairs=[];
        this.nechetDepartPairs=[];
        this.GetPairs();
    }

    public GetPairs(){
        this.departmentService.GetChetPairs()
            .subscribe((res: any) => {
                    this.chetDepartPairs= res;
                    this.sortDepartPairs(this.chetDepartPairs);
                },
                error => console.error(error))


        this.departmentService.GetNechetPairs()
            .subscribe((res: any) => {
                    this.nechetDepartPairs= res;
                    this.sortDepartPairs(this.nechetDepartPairs);
                },
                error => console.error(error))
    }

    public sortDepartPairs(listDepartPairs:DepartmentPair[]){
        for (var i=0; i < listDepartPairs.length; i++){
            listDepartPairs[i].pairs=this.sortProfPairs(listDepartPairs[i].pairs);
        }
    }

    public sortProfPairs(listPairs:Pair[]):Array<Pair>{

        var newListProfPairs: Pair[];
        newListProfPairs=[];

        for(var i=0;listPairs.length;i++){

            if(listPairs[i].dayofweek=="Понедельник"){
                switch (listPairs[i].pairnumber) {
                    case 1:
                        newListProfPairs.splice(0, 1, listPairs[i]);
                        break;
                    case 2:
                        newListProfPairs.splice(1, 1, listPairs[i]);
                        break;
                    case 3:
                        newListProfPairs.splice(2, 1, listPairs[i]);
                        break;
                    case 4:
                        newListProfPairs.splice(3, 1, listPairs[i]);
                        break;
                    case 5:
                        newListProfPairs.splice(4, 1, listPairs[i]);
                        break;
                    case 6:
                        newListProfPairs.splice(5, 1, listPairs[i]);
                        break;
                    default:
                        break;
                }
            }

            if(listPairs[i].dayofweek=="Вторник"){
                switch (listPairs[i].pairnumber) {
                    case 1:
                        newListProfPairs.splice(6, 1, listPairs[i]);
                        break;
                    case 2:
                        newListProfPairs.splice(7, 1, listPairs[i]);
                        break;
                    case 3:
                        newListProfPairs.splice(8, 1, listPairs[i]);
                        break;
                    case 4:
                        newListProfPairs.splice(9, 1, listPairs[i]);
                        break;
                    case 5:
                        newListProfPairs.splice(10, 1, listPairs[i]);
                        break;
                    case 6:
                        newListProfPairs.splice(11, 1, listPairs[i]);
                        break;
                    default:
                        break;
                }
            }

            if(listPairs[i].dayofweek=="Среда"){
                switch (listPairs[i].pairnumber) {
                    case 1:
                        newListProfPairs.splice(12, 1, listPairs[i]);
                        break;
                    case 2:
                        newListProfPairs.splice(13, 1, listPairs[i]);
                        break;
                    case 3:
                        newListProfPairs.splice(14, 1, listPairs[i]);
                        break;
                    case 4:
                        newListProfPairs.splice(15, 1, listPairs[i]);
                        break;
                    case 5:
                        newListProfPairs.splice(16, 1, listPairs[i]);
                        break;
                    case 6:
                        newListProfPairs.splice(17, 1, listPairs[i]);
                        break;
                    default:
                        break;
                }
            }

            if(listPairs[i].dayofweek=="Четверг"){
                switch (listPairs[i].pairnumber) {
                    case 1:
                        newListProfPairs.splice(18, 1, listPairs[i]);
                        break;
                    case 2:
                        newListProfPairs.splice(19, 1, listPairs[i]);
                        break;
                    case 3:
                        newListProfPairs.splice(20, 1, listPairs[i]);
                        break;
                    case 4:
                        newListProfPairs.splice(21, 1, listPairs[i]);
                        break;
                    case 5:
                        newListProfPairs.splice(22, 1, listPairs[i]);
                        break;
                    case 6:
                        newListProfPairs.splice(23, 1, listPairs[i]);
                        break;
                    default:
                        break;
                }
            }

            if(listPairs[i].dayofweek=="Пятница"){
                switch (listPairs[i].pairnumber) {
                    case 1:
                        newListProfPairs.splice(24, 1, listPairs[i]);
                        break;
                    case 2:
                        newListProfPairs.splice(25, 1, listPairs[i]);
                        break;
                    case 3:
                        newListProfPairs.splice(26, 1, listPairs[i]);
                        break;
                    case 4:
                        newListProfPairs.splice(27, 1, listPairs[i]);
                        break;
                    case 5:
                        newListProfPairs.splice(28, 1, listPairs[i]);
                        break;
                    case 6:
                        newListProfPairs.splice(29, 1, listPairs[i]);
                        break;
                    default:
                        break;
                }
            }

            if(listPairs[i].dayofweek=="Суббота"){
                switch (listPairs[i].pairnumber) {
                    case 1:
                        newListProfPairs.splice(30, 1, listPairs[i]);
                        break;
                    case 2:
                        newListProfPairs.splice(31, 1, listPairs[i]);
                        break;
                    case 3:
                        newListProfPairs.splice(32, 1, listPairs[i]);
                        break;
                    case 4:
                        newListProfPairs.splice(33, 1, listPairs[i]);
                        break;
                    // case 5:
                    //     newListProfPairs.splice(34, 1, listPairs[i]);
                    //     break;
                    // case 6:
                    //     newListProfPairs.splice(35, 1, listPairs[i]);
                    //     break;
                    default:
                        break;
                }
            }
        }

        return newListProfPairs;

    }

}



