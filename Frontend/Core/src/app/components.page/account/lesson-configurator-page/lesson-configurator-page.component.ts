import {Component, EventEmitter, Injectable, OnInit, Output} from '@angular/core';
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/auth.service";
import {ScheduleService} from "../../../services/schedule.service";
import {AccountService} from "../../../services/account.service";
import {Professor} from "../../../models/account/professor";
import {Lesson} from "../../../models/shedule/lesson";
import {SemesterNumberYear} from "../../../models/semesterNumberYear.model";

@Component({
    selector: 'lesson-configurator-page',
    templateUrl: './lesson-configurator-page.component.html',
    styleUrls: ['./lesson-configurator-page.component.css']
})
@Injectable()
export class LessonConfiguratorPageComponent implements OnInit {

    @Output() toogleViewMenu: EventEmitter<any> = new EventEmitter();
    public user: User;
    public professor: Professor;
    public selectLesson: Lesson;
    public lessons: Array<Lesson> = new Array<Lesson>();
    public showLoader: boolean = false;

    public semesterNumberYear: SemesterNumberYear = new SemesterNumberYear();

    constructor(private authenticationService: AuthenticationService,
                private accountService: AccountService,
                private ScheduleService: ScheduleService) {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                this.accountService.GetProfessorByUser(this.user.id).subscribe(
                    resultProf => {
                        this.professor = resultProf.data;
                    }
                );
            });
    }

    ngOnInit(): void {
        this.selectLesson = null;
    }

    loadLesson() {
        if (this.professor == null || this.professor.id == 0)
            return;
        this.showLoader = true;
        this.ScheduleService.GetProfessorLessons(this.professor.id, this.semesterNumberYear).subscribe(
            result => {
                this.showLoader = false;
                this.lessons = result.data;
            }, error1 => {
                this.showLoader = false;
            }
        );
    }

    onClick(lesson: Lesson) {
        if (lesson.id !== 0) {
            this.selectLesson = lesson;
            this.toogleViewMenu.emit();
        }
    }

    back() {
        this.selectLesson = null;
        this.toogleViewMenu.emit();
    }

}
