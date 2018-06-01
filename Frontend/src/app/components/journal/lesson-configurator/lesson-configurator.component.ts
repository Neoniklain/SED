import {Component, Injectable, Input, OnInit} from '@angular/core';
import {Pair} from "../../../models/shedule/pair";
import {AuthenticationService} from "../../../services/authService";
import {PairService} from "../../../services/pair.service";
import {NotificationService} from "../../../services/notification.service";

@Component({
    selector: 'lesson-configurator',
    templateUrl: './lesson-configurator.component.html',
    styleUrls: ['./lesson-configurator.component.css']
})
@Injectable()
export class LessonСonfiguratorComponent implements OnInit {

    @Input() pair: Pair;

    constructor(private authenticationService: AuthenticationService,
                private pairService: PairService,
                private notificationService: NotificationService) {
    }

    ngOnInit(): void {

    }

}






