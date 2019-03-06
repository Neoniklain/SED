import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {SemesterNumberYear} from "../../../models/semesterNumberYear.model";

@Component({
    selector: 'semester-picker',
    templateUrl: "./semesterPicker.component.html",
    styleUrls: ['./semesterPicker.component.css']
})

export class SemesterPickerComponent implements OnInit {

    @Input()
    semesterNumberYear: SemesterNumberYear = new SemesterNumberYear();
    @Output()
    semesterNumberYearChanged = new EventEmitter<SemesterNumberYear>();
    @Output()
    ngModelChange = new EventEmitter<SemesterNumberYear>();

    yearRange = [];
    semesterRange = [{label: 1, value: 1}, {label: 2, value: 2}];

    constructor() {
    }

    ngOnInit() {
        let currentYear = new Date(Date.now()).getFullYear();
        for (let year = currentYear - 10; year <= currentYear + 10; year++) {
            this.yearRange.push({label: year, value: year});
        }
        this.current();
    }

    onSemesterNumberYearChanged(model: SemesterNumberYear) {
        this.semesterNumberYear = model;
        this.semesterNumberYearChanged.emit(model);
        this.ngModelChange.emit(model);
    }

    changeSemester(newSemester: number) {
        this.semesterNumberYear.semester = newSemester;
        this.onSemesterNumberYearChanged(this.semesterNumberYear);
    }

    changeYear(newYeart: number) {
        this.semesterNumberYear.year = newYeart;
        this.onSemesterNumberYearChanged(this.semesterNumberYear);
    }

    back() {
        if (this.semesterNumberYear.semester == 1) {
            this.semesterNumberYear.semester++;
        } else {
            this.semesterNumberYear.year--;
            this.semesterNumberYear.semester--;
        }
        this.onSemesterNumberYearChanged(this.semesterNumberYear);
    }

    current() {
        this.semesterNumberYear.year = new Date(Date.now()).getFullYear();;
        this.semesterNumberYear.semester = new Date(Date.now()).getMonth() < 8 ? 2 : 1;
        this.onSemesterNumberYearChanged(this.semesterNumberYear);
    }

    next() {
        if (this.semesterNumberYear.semester == 1) {
            this.semesterNumberYear.semester++;
            this.semesterNumberYear.year++;
        } else {
            this.semesterNumberYear.semester--;
        }
        this.onSemesterNumberYearChanged(this.semesterNumberYear);
    }
}