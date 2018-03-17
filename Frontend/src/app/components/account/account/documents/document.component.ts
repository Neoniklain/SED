import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {Issue} from "../../../../models/workflow/issue.model";
import {IssueService} from "../../../../services/issues.service";
import {AuthenticationService} from "../../../../services/authService";
import {User} from "../../../../models/account/user.model";
import {AccountService} from "../../../../services/accountService";
import {FileUploader, FileUploaderOptions} from 'ng2-file-upload';

// upload url
const URL = 'http://localhost:8080/api/excel/ParseStudyPlan';
const MB = 1024 * 1024;

@Component({
   selector: 'document-page',
   templateUrl: './document.component.html',
   styleUrls: ['./document.component.css']
})

export class DocumentComponent {
   public displayDialog: boolean = false;
   public displayChangeDialog: boolean = false;
   public tempIssue: Issue = new Issue();
   public issueName: string;
   public issueList: Array<Issue>;
   public executors: User[] = [];
   public results: User[] = [];
   public _uploader: FileUploader;

   constructor(private issueService: IssueService,
               private router: Router,
               private accountService: AccountService,
               private authService: AuthenticationService) {
   }

   ngOnInit(): void {
      this.issueList = new Array();
      this.UpdateIssueList();
   }

   CreateIssue() {
      this.displayDialog = false;
      let newIssue: Issue = new Issue();
      newIssue.collaborators = this.executors;
      newIssue.name = this.issueName;
      this.issueService.Create(newIssue).subscribe((res: any) => {
             this.UpdateIssueList();
          },
          (error: any) => {
             console.log("Ошибка" + error);
          });
   }

   DeleteIssue(id) {
      this.issueService.Delete(id).subscribe((res: any) => {
             this.UpdateIssueList();
          },
          (error: any) => {
             console.log("Ошибка" + error);
          });
   }

   UpdateIssueList() {
      this.issueService.GetList().subscribe((res: any) => {
             this.issueList = res;
          },
          (error: any) => {
             console.log("Ошибка" + error);
          });
   }

   public searchUser(event: any) {
      let query = event.query.substring(0, 60);
      this.accountService.FindUsersByFIO(query)
          .subscribe((res: any) => {
                 this.results = res;
              },
              (error: any) => {
                 console.log("Ошибка" + error);
              });
   }

   public ChangeIssue(IssueID: number) {
      this.displayChangeDialog = true;
      this.issueService.Get(IssueID)
          .subscribe((res: any) => {
                 this.tempIssue = res;
                 this.issueName = this.tempIssue.name;
                 this.executors = this.tempIssue.collaborators;
              },
              (error: any) => {
                 console.log("Ошибка" + error);
              });
   }

   public UpdateIssue() {
      this.tempIssue.name = this.issueName;
      this.tempIssue.collaborators = this.executors;
      this.issueService.Update(this.tempIssue)
          .subscribe((res: any) => {
                 this.displayChangeDialog = false;
                 this.tempIssue = new Issue();
              },
              (error: any) => {
                 console.log("Ошибка" + error);
              });
   }

}
