import {Student} from "../account/student";
import {Lesson} from "../shedule/lesson";

export class CertificationReport {
    lesson: Lesson;
    studentCertification: Array<CertificationStudent>;
    allHours: number;

    constructor() {
        this.lesson = new Lesson();
        this.studentCertification = [];
        this.allHours = 0;
    }
}


export class CertificationStudent {
    /* Студент */
    student: Student;
    /* Атестация */
    value: number;
    /* Пропущено часов */
    missingHours: number;

    constructor() {
        this.student = new Student();
        this.value = 0;
        this.missingHours = 0;
    }
}
