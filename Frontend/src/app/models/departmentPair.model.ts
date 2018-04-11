import {Pair} from "./pair.model";
export class DepartmentPair{
    public prof_fio: string;
    public pairs: Pair[];

    constructor(){
        this.prof_fio="";
        this.pairs=[];
    }
}