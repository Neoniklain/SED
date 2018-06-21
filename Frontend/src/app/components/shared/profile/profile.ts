import {Component, OnInit, OnChanges, Input, Inject} from "@angular/core";
import {User} from "../../../models/account/user.model";

@Component({
   selector: 'profile',
   templateUrl: "./profile.html",
   styleUrls: ["./profile.css"],
})

export class ProfileComponent implements OnInit {

   @Input() user: User = new User();

    constructor( @Inject('profileConfig') private config ) {
    }

   ngOnInit() {

   }

}