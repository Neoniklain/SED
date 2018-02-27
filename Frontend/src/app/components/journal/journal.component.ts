import {Component, ContentChild, ElementRef, Injectable, NgModule, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user.model";
import {AuthenticationService} from "../../services/authService";
import {JournalService} from "../../services/journal.service";
import {Journal} from "../../models/journal/journal.model";

@Component({
  selector: 'journal-page',
  templateUrl: './journal.component.html',
  styleUrls: ['./journal.component.css']
})
@Injectable()
export class JournalComponent implements OnInit {

  public user: User;
  public journal: Journal;

  constructor(private authenticationService: AuthenticationService,
              private journalService: JournalService,
              private router: Router) {
    this.user = new User();
    this.authenticationService.getUser().subscribe(
        res => {
          this.user = res;
        },
      error => {
        if (error.statusText === "Forbidden")
          this.router.navigate(['/404']);
      });
  }

  ngOnInit(): void {
     this.journalService.GetAll().subscribe(
         journal => {
            this.journal = journal;
            console.log("journal", journal);
         }, error => console.log(error)
     );
  }


}
