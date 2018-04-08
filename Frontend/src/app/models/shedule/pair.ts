import {Group} from "../group";

export class Pair {
    public pairnumber: number;
    public weektype: String;
    public dayofweek: String;
    public professor: String;
    public room: String;
    public discipline: String;
    public group: Group;
    public department: String;

    constructor() {
        this.pairnumber = 0;
        this.weektype = "";
        this.dayofweek = "";
        this.professor = "";
        this.room = "";
        this.discipline = "";
        this.group = new Group();
        this.department = "";
    }
}
