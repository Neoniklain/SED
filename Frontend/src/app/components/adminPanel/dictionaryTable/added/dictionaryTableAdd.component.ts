import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {isUndefined} from "util";
import {Dictionary} from "../../../../models/admin/dictionary.model";
import {DictionaryService} from "../../../../services/dictionary.service";
import {Role} from "../../../../models/account/role.model";
import {Discipline} from "../../../../models/discipline";
import {UserCreate} from "../../../../models/account/user.model";
import {AuthenticationService} from "../../../../services/authService";
import {MessageService} from "primeng/components/common/messageservice";
import {Group} from "../../../../models/group";
import {FieldOfKnowledge} from "../../../../models/fieldOfKnowledge";
import {Institute} from "../../../../models/institute";
import {Department} from "../../../../models/department";
import {ToastrService} from "ngx-toastr";
import {UtilsService} from "../../../../services/utils.service";

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
    @Input() model: any;
    @Output() loadData = new EventEmitter();
    @Output() canelEditable = new EventEmitter();

    public roles: Array<Role>;
    public findRoles: Array<Role>;
    public fieldOfKnowledges: Array<FieldOfKnowledge>;
    public institutes: Array<Institute>;
    public departments: Array<Department>;
    public Dictionary;
    public columnsName: string[];

    constructor(private router: Router,
                private dictionaryService: DictionaryService,
                private utilsService: UtilsService,
                private authenticationService: AuthenticationService,
                private toastr: ToastrService) { }

    ngOnInit() {
        this.Dictionary = Dictionary;
        this.columnsName = Object.keys(this.model);
        this.changeType();
    }
    ngOnChanges() {
        this.columnsName = Object.keys(this.model);
        this.changeType();
        if (!isUndefined(this.editableModel) && this.editableModel !== null) {
         this.model = this.editableModel;
      }
        if (!isUndefined(this.deleteModel) && this.deleteModel !== null) {
         this.Delete();
      }
    }
    Add() {
       this.AddOrUpdate();
    }
    Save() {
       this.AddOrUpdate();
       this.canelEditable.emit();
    }
    Delete() {
      switch (this.type.toString()) {
          case Dictionary.disciplines.toString():
            this.dictionaryService.DeleteDiscipline(this.deleteModel.id).subscribe(
                result => {
                    this.model = new Discipline();
                    this.deleteModel = null;
                    if (result.status === 'ok') {
                        this.showMessage('success', 'Дисциплины обновлены.');
                        this.loadData.emit();
                    }
                    if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                }, error => {
                   this.deleteModel = null;
                    this.showMessage('error', 'Ошибка выполнения.');
                });
            break;
          case Dictionary.groups.toString():
              this.dictionaryService.DeleteGroup(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Group();
                      this.deleteModel = null;
                      if (result.status === 'ok') {
                          this.showMessage('success', 'Группы обновлены.');
                          this.loadData.emit();
                      }
                      if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                  }, error => {
                      this.deleteModel = null;
                      this.showMessage('error', 'Ошибка выполнения.');
                  });
              break;
          case Dictionary.institutes.toString():
              this.dictionaryService.DeleteInstitute(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Institute();
                      this.deleteModel = null;
                      if (result.status === 'ok') {
                          this.showMessage('success', 'Институты обновлены.');
                          this.loadData.emit();
                      }
                      if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                  }, error => {
                      this.deleteModel = null;
                      this.showMessage('error', 'Ошибка выполнения.');
                  });
              break;
          case Dictionary.departments.toString():
              this.dictionaryService.DeleteDepartment(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Department();
                      this.deleteModel = null;
                      if (result.status === 'ok') {
                          this.showMessage('success', 'Кафедры обновлены.');
                          this.loadData.emit();
                      }
                      if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                  }, error => {
                      this.deleteModel = null;
                      this.showMessage('error', 'Ошибка выполнения.');
                  });
              break;
      }
    }
    Cancel() {
      this.changeType();
      this.canelEditable.emit();
    }
    AddOrUpdate() {
        switch (this.type.toString()) {
            case Dictionary.users.toString():
                this.authenticationService.register(this.model).subscribe(
                    result => {
                        this.model = new UserCreate();
                        if (result.status === 'ok') {
                            this.showMessage('success', 'Пользователи обновлены.');
                            this.loadData.emit();
                        }
                        if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                    }, error => {
                        this.showMessage('error', 'Ошибка выполнения.');
                    });
                break;
            case Dictionary.disciplines.toString():
                this.dictionaryService.AddOrUpdateDiscipline(this.model).subscribe(
                    result => {
                        this.model = new Discipline();
                        if (result.status === 'ok') {
                            this.showMessage('success', 'Дисциплины обновлены.');
                            this.loadData.emit();
                        }
                        if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                    }, error => {
                        this.showMessage('error', 'Ошибка выполнения.');
                    });
                break;
            case Dictionary.groups.toString():
                this.dictionaryService.AddOrUpdateGroups(this.model).subscribe(
                    result => {
                        this.model = new Group();
                        if (result.status === 'ok') {
                            this.showMessage('success', 'Группы обновлены.');
                            this.loadData.emit();
                        }
                        if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                    }, error => {
                        this.showMessage('error', 'Ошибка выполнения.');
                    });
                break;
            case Dictionary.institutes.toString():
                this.dictionaryService.AddOrUpdateInstitute(this.model).subscribe(
                    result => {
                        this.model = new Institute();
                        if (result.status === 'ok') {
                            this.showMessage('success', 'Институты обновлены.');
                            this.loadData.emit();
                        }
                        if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                    }, error => {
                        this.showMessage('error', 'Ошибка выполнения.');
                    });
                break;
            case Dictionary.departments.toString():
                this.dictionaryService.AddOrUpdateDepartment(this.model).subscribe(
                    result => {
                        this.model = new Department();
                        if (result.status === 'ok') {
                            this.showMessage('success', 'Кафедры обновлены.');
                            this.loadData.emit();
                        }
                        if (result.status === 'error') this.showMessage('error', 'Ошибка выполнения.');
                    }, error => {
                        this.showMessage('error', 'Ошибка выполнения.');
                    });
                break;
        }
    }
    changeType() {
      switch (this.type.toString()) {
         case Dictionary.users.toString():
               this.dictionaryService.GetRoles().subscribe(result => { this.roles = result.content; });
            break;
         case Dictionary.disciplines.toString():
               this.dictionaryService.GetFieldOfKnowladge().subscribe(result => { this.fieldOfKnowledges = result.content; });
            break;
         case Dictionary.institutes.toString():
            break;
         case Dictionary.departments.toString():
             this.dictionaryService.GetInstitutes().subscribe(result => { this.institutes = result.content; });
            break;
         case Dictionary.groups.toString():
             this.dictionaryService.GetDepartments().subscribe(result => { this.departments = result.content; });
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
    showMessage(type, text) {
        if (type === 'success')
            this.toastr.success(text, "Успешно");

        if (type === 'error')
            this.toastr.error(text, "Ошибка");
    }

    createRandomUser() {
        this.model.username = this.utilsService.getNickname();
        this.model.userFIO = this.utilsService.getFIO();
        this.model.email = this.model.username + "@mail.ru";
        this.model.password = this.utilsService.generatePassword(8);
    }
}
