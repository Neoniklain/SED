import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Lesson} from "../../../models/shedule/lesson";

@Component({
    selector: 'lesson-details',
    templateUrl: './lessonDetails.component.html',
    styleUrls: ['./lessonDetails.component.css']
})

@Injectable()
export class LessonDetailsComponent implements OnInit {

    @Input() lesson: Lesson = new Lesson();
    @Output() clickOnLesson = new EventEmitter<any>();

    clickable: boolean = false;

    constructor() { }

    ngOnInit(): void {
        if (this.clickOnLesson.observers.length > 0)
            this.clickable = true;
    }

    onClick(lesson: Lesson) {
        if (this.clickable)
            this.clickOnLesson.emit(lesson);
    }

}
