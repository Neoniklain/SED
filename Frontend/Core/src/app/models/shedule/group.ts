import {Speciality} from "./speciality";

export class Group {
    public id: number;
    public name: string;
    public speciality: Speciality;

    constructor() {
        this.id = 0;
        this.name = "";
        this.speciality = new Speciality();
    }
}
