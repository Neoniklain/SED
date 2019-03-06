import {Speciality} from "./shedule/speciality";

export class EducationPeriod {
    id: number;
    startDate: Date;
    speciality: Speciality;
    endDate: Date;

    constructor() {
        this.id = 0;
        this.startDate = new Date();
        this.speciality = new Speciality();
        this.endDate = new Date();
    }
}