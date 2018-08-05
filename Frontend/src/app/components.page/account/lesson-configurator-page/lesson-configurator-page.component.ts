import {Component, Injectable, OnInit} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {Journal} from "../../../models/journal/journal.model";
import {User} from "../../../models/account/user.model";
import {AuthenticationService} from "../../../services/authService";
import {JournalService} from "../../../services/journal.service";
import {ScheduleService} from "../../../services/schedule.service";
import {AccountService} from "../../../services/accountService";
import {Professor} from "../../../models/account/professor";
import {Lesson} from "../../../models/shedule/lesson";

@Component({
    selector: 'lesson-configurator-page',
    templateUrl: './lesson-configurator-page.component.html',
    styleUrls: ['./lesson-configurator-page.component.css']
})
@Injectable()
export class LessonConfiguratorPageComponent implements OnInit {

    public user: User;
    public professor: Professor;
    public selectLesson: Lesson;
    public lessons: Array<Lesson> = new Array<Lesson>();
    public showLoader: boolean = false;


    constructor(private authenticationService: AuthenticationService,
                private accountService: AccountService,
                private ScheduleService: ScheduleService) {
        this.user = new User();
        this.authenticationService.getUser().subscribe(
            res => {
                this.user = res.data;
                this.showLoader = true;
                this.accountService.GetProfessorByUser(this.user.id).subscribe(
                    resultProf => {
                        this.professor = resultProf.data;
                            this.ScheduleService.GetProfessorLessons(this.professor.id).subscribe(
                            result => {
                                this.showLoader = false;
                                this.lessons = result.data;
                            }
                        );
                    }
                );
            });
    }

    ngOnInit(): void {
        this.selectLesson = null;
    }

    onClick(lesson: Lesson) {
        if (lesson.id !== 0)
            this.selectLesson = lesson;
    }

    back() {
        this.selectLesson = null;
    }

}
