import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Lesson} from "../../../models/shedule/lesson";

@Component({
    selector: 'lessons-list',
    templateUrl: './lessonsList.component.html',
    styleUrls: ['./lessonsList.component.css']
})

@Injectable()
export class LessonListComponent implements OnInit {

    @Input() lessons: Array<Lesson> = new Array<Lesson>();
    @Output() clickOnLesson = new EventEmitter<any>();

    constructor() { }

    ngOnInit(): void {
    }

    onClick(lesson: Lesson) {
        this.clickOnLesson.emit(lesson);
    }

}
