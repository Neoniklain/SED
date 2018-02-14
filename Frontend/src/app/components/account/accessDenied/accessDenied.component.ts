import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../services/authService";
import {RouteConstants} from "../../../bootstrap/app.route.constants";

@Component({
  selector: 'accessDenied-page',
  templateUrl: './accessDenied.component.html',
  styleUrls: ['./accessDenied.component.css']
})
@Injectable()
export class AccessDeniedComponent implements OnInit {

  constructor(private router: Router) {

  }

   ngOnInit(): void { }

   Main() {
      this.router.navigate([RouteConstants.News.All]);
   }

}
