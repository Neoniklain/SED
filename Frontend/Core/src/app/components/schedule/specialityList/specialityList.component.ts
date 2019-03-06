import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Lesson} from "../../../models/shedule/lesson";
import {Speciality} from "../../../models/shedule/speciality";

@Component({
    selector: 'speciality-list',
    templateUrl: './specialityList.component.html',
    styleUrls: ['./specialityList.component.css']
})

@Injectable()
export class SpecialityListComponent implements OnInit {

    @Input() specialities: Array<Speciality> = new Array<Speciality>();
    @Output() clickOnSpeciality = new EventEmitter<any>();

    constructor() { }

    ngOnInit(): void {
    }

    onClick(speciality: Speciality) {
        this.clickOnSpeciality.emit(speciality);
    }

}
