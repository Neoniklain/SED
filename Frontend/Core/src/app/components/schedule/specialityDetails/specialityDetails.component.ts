import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';
import {Speciality} from "../../../models/shedule/speciality";

@Component({
    selector: 'speciality-details',
    templateUrl: './specialityDetails.component.html',
    styleUrls: ['./specialityDetails.component.css']
})

@Injectable()
export class SpecialityDetailsComponent implements OnInit {

    @Input() speciality: Speciality = new Speciality();
    @Output() clickOnSpeciality = new EventEmitter<any>();

    clickable: boolean = false;

    constructor() { }

    ngOnInit(): void {
        if (this.clickOnSpeciality.observers.length > 0)
            this.clickable = true;
    }

    onClick(speciality: Speciality) {
        if (this.clickable)
            this.clickOnSpeciality.emit(speciality);
    }

}
