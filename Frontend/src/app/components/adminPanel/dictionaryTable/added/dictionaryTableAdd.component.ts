import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {LazyLoadEvent} from "primeng/api";
import {isUndefined} from "util";
import {Dictionary} from "../../../../models/admin/dictionary.model";
import {DictionaryService} from "../../../../services/dictionary.service";
import {Role} from "../../../../models/account/role.model";
import {Discipline} from "../../../../models/discipline.model";
import {User, UserCreate} from "../../../../models/account/user.model";
import {AccountService} from "../../../../services/accountService";
import {AuthenticationService} from "../../../../services/authService";
import {MessageService} from "primeng/components/common/messageservice";
import {DatePipe} from "@angular/common";

@Component({
   selector: 'dictionary-table-add',
   templateUrl: "./dictionaryTableAdd.component.html",
   styleUrls: ["./dictionaryTableAdd.component.css"],
})

export class DictionaryTableAddComponent implements OnInit, OnChanges {

   @Input() type;
   @Input() editableModel;
   @Input() deleteModel;
   @Input() editMode: boolean = false;
   @Output() loadData = new EventEmitter();
   @Output() canelEditable = new EventEmitter();

   public roles: Array<Role>;
   public findRoles: Array<Role>;
   public fileOfKnowlages: Array<Role>;

   public Model: any;

   constructor(private router: Router,
               private dictionaryService: DictionaryService,
               private authenticationService: AuthenticationService,
               private messageService: MessageService) { }

   ngOnInit() {
      this.changeType();
   }

   ngOnChanges() {
      this.changeType();
      if (!isUndefined(this.editableModel) && this.editableModel !== null) {
         this.Model = this.editableModel;
      }
      if (!isUndefined(this.deleteModel) && this.deleteModel !== null) {
         this.Delete();
      }
   }

   AddOrUpdate() {
      switch (this.type) {
         case Dictionary[Dictionary.users]:
            this.authenticationService.register(this.Model).subscribe(
                result => {
                   this.Model = new UserCreate();
                   this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Пользователи обновлены.'});
                   this.loadData.emit();
                }, error => {
                   this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Ошибка выполнения.'});
                });
            break;
         case Dictionary[Dictionary.disciplines]:
            this.dictionaryService.AddOrUpdateDiscipline(this.Model).subscribe(
                result => {
                   this.Model = new Discipline();
                   console.log("result", result);
                   if (result.status === "ok") {
                      this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Дисциплины обновлены.'});
                      this.loadData.emit();
                   }
                   else
                     this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Ошибка выполнения.'});
                }, error => {
                   this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Ошибка выполнения.'});
                });
            break;
      }
   }

   Delete() {
      switch (this.type) {
         case Dictionary[Dictionary.disciplines]:
            this.dictionaryService.DeleteDiscipline(this.deleteModel.id).subscribe(
                result => {
                   this.Model = new Discipline();
                   this.deleteModel = null;
                   if (result.status === "ok") {
                      this.messageService.add({severity: 'success', summary: 'Успешно.', detail: 'Дисциплины обновлены.'});
                      this.loadData.emit();
                   }
                   else
                      this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Ошибка выполнения.'});
                }, error => {
                   this.deleteModel = null;
                   this.messageService.add({severity: 'error', summary: 'Ошибка.', detail: 'Ошибка выполнения.'});
                });
            break;
      }
   }

   Cancel() {
      this.changeType();
      this.canelEditable.emit();
   }

   changeType() {
      switch (this.type) {
         case Dictionary[Dictionary.users]:
            this.Model = new UserCreate();
            this.dictionaryService.GetRoles().subscribe(result => { this.roles = result.content; });
            break;
         case Dictionary[Dictionary.disciplines]:
            this.Model = new Discipline();
            console.log("this.Model", this.Model);
            this.dictionaryService.GetFieldOfKnowlage().subscribe(result => { this.fileOfKnowlages = result.content; console.log(this.fileOfKnowlages); });
            break;
      }
   }

   public searchRoles(event: any) {
      let query = event.query.substring(0, 60);
      this.findRoles = this.roles;
      let newRoles: Array<Role> = new Array();
      for (let role of this.roles){
         if (role.roleName.toLowerCase().indexOf(query.toLowerCase()) != -1) {
            newRoles.push(role);
         }
      }
      if (newRoles.length > 0)
         this.findRoles = newRoles;
   }

}
