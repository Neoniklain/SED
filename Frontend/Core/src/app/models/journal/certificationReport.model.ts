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
    /* Баллы за посещения */
    visitationValue: number;
    /* Пропущено часов */
    missingHours: number;
    /* События */
    eventValue: Array<CertificationEvent>;

    constructor() {
        this.student = new Student();
        this.value = 0;
        this.visitationValue = 0;
        this.missingHours = 0;
        this.eventValue = [];
    }
}

export class CertificationEvent {
    event: string;
    value: number;

    constructor() {
        this.event = "";
        this.value = 0;
    }
}
