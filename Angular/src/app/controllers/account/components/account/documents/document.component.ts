import {Component, Input} from '@angular/core';
import {DialogModule} from "primeng/primeng";
import {ButtonModule} from "primeng/primeng";
import {IssueService} from "../../../../../core/services/issues.service";
import {Router} from "@angular/router";
import {Issue} from "../../../../../models/issue.model";


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

  constructor(
    private issueService:IssueService,
    private router:Router
  )
  {
  }

  ngOnInit(): void {
    this.UpdateIssueList();
  }
  CreateIssue() {
    this.displayDialog=false;
    this.issueService.Create(this.issueName).subscribe((res:any)=> {
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

}
