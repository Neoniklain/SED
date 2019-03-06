export class SemesterNumberYear {
    year: number;
    semester: number;

    constructor() {
        this.year = new Date(Date.now()).getFullYear();
        this.semester = 1;
    }
}