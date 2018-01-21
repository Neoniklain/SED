import {Component, Input} from '@angular/core';
import {DialogModule} from "primeng/primeng";
import {ButtonModule} from "primeng/primeng";
//import { AutoCompleteModule } from 'primeng/components/autocomplete/autocomplete';
import {IssueService} from "../../../../../core/services/issues.service";
import {AccountService} from "../../../../../core/services/account.service";
import {Router} from "@angular/router";
import {Issue} from "../../../../../models/issue.model";
import {User} from "../../../../../models/user.model";


@Component({
  selector: 'document-page',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})

export class DocumentComponent{

  private show:boolean = false;
  public displayDialog: boolean = false;
  public issueName: string;
  public issueList: Array<Issue>;
  public executors: User[] = [];
  public results: User[] = [];

  constructor(
    private issueService:IssueService,
    private router:Router,
    private accountService:AccountService
  )
  {
  }

  ngOnInit(): void {
    this.UpdateIssueList();
  }
  CreateIssue() {
    this.displayDialog=false;
    let newIssue: Issue = new Issue();
    newIssue.collaborators = this.executors;
    newIssue.name=this.issueName;
    this.issueService.Create(newIssue).subscribe((res:any)=> {
          console.info("res",res);
        this.UpdateIssueList();
        },
        (error:any)=> {
          console.log("Ошибка"+error);
        });
  }
  DeleteIssue(id) {
    this.issueService.Delete(id).subscribe((res:any)=> {
        console.info("res",res);
        this.UpdateIssueList();
      },
      (error:any)=> {
        console.log("Ошибка"+error);
      });
  }

  UpdateIssueList() {
    this.issueService.GetList().subscribe((res:any)=> {
        console.log("GetList_res", res);
        this.issueList = res;
        console.log("GetList_res", this.issueList);
      },
      (error:any)=> {
        console.log("Ошибка"+error);
      });
  }
  public Show() {
    this.show = true;
  }
  public Hide() {
    this.show = false;
  }
  public toogle() {
    if(this.show==false)
      this.show=true;
    else
      this.show=false;
  }

  public searchUser(event:any)
  {
    let query = event.query.substring(0, 60);
    this.accountService.FindUserByName(query)
      .subscribe((res:any)=> {
          console.log("FindUserByName result:", res);
          this.results = res;
        },
        (error:any)=> {
          console.log("Ошибка"+error);
        });
  }
}
