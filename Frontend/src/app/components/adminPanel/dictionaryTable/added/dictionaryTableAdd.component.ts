import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from "@angular/core";
import {Router} from "@angular/router";
import {isUndefined} from "util";
import {Dictionary} from "../../../../models/admin/dictionary.model";
import {DictionaryService} from "../../../../services/dictionary.service";
import {Role} from "../../../../models/account/role.model";
import {Discipline} from "../../../../models/shedule/discipline";
import {UserCreate} from "../../../../models/account/user.model";
import {AuthenticationService} from "../../../../services/authService";
import {MessageService} from "primeng/components/common/messageservice";
import {Group} from "../../../../models/shedule/group";
import {FieldOfKnowledge} from "../../../../models/shedule/fieldOfKnowledge";
import {Institute} from "../../../../models/shedule/institute";
import {Department} from "../../../../models/shedule/department";
import {ToastrService} from "ngx-toastr";
import {UtilsService} from "../../../../services/utils.service";
import {Professor} from "../../../../models/account/professor";
import {Room} from "../../../../models/shedule/room.model";
import {StatusType} from "../../../../models/statusType.model";
import {NotificationService} from "../../../../services/notification.service";

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

    public fieldOfKnowledges: Array<FieldOfKnowledge>;
    public institutes: Array<Institute>;
    public departments: Array<Department>;
    public Dictionary;
    public columnsName: string[];

    constructor(private notification: NotificationService,
                private router: Router,
                private dictionaryService: DictionaryService,
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
                    if (result.status === StatusType.OK.toString()) {
                        this.loadData.emit();
                    }
                    this.notification.FromStatus(result);
                });
            break;
          case Dictionary.groups.toString():
              this.dictionaryService.DeleteGroup(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Group();
                      this.deleteModel = null;
                      if (result.status === StatusType.OK.toString()) {
                          this.loadData.emit();
                      }
                      this.notification.FromStatus(result);
                  });
              break;
          case Dictionary.institutes.toString():
              this.dictionaryService.DeleteInstitute(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Institute();
                      this.deleteModel = null;
                      if (result.status === StatusType.OK.toString()) {
                          this.loadData.emit();
                      }
                      this.notification.FromStatus(result);
                  });
              break;
          case Dictionary.departments.toString():
              this.dictionaryService.DeleteDepartment(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Department();
                      this.deleteModel = null;
                      if (result.status === StatusType.OK.toString()) {
                          this.loadData.emit();
                      }
                      this.notification.FromStatus(result);
                  });
              break;
          case Dictionary.rooms.toString():
              this.dictionaryService.DeleteRoom(this.deleteModel.id).subscribe(
                  result => {
                      this.model = new Department();
                      this.deleteModel = null;
                      if (result.status === StatusType.OK.toString()) {
                          this.loadData.emit();
                      }
                      this.notification.FromStatus(result);
                  });
              break;
      }
    }
    Cancel() {
        this.canelEditable.emit();
        this.changeType();
    }
    AddOrUpdate() {
        switch (this.type.toString()) {
            case Dictionary.disciplines.toString():
                console.log("dis", this.model);
                this.dictionaryService.AddOrUpdateDiscipline(this.model).subscribe(
                    result => {
                        this.model = new Discipline();
                        if (result.status === StatusType.OK.toString()) {
                            this.loadData.emit();
                            this.canelEditable.emit();
                        }
                        this.notification.FromStatus(result);
                    });
                break;
            case Dictionary.groups.toString():
                this.dictionaryService.AddOrUpdateGroups(this.model).subscribe(
                    result => {
                        this.model = new Group();
                        if (result.status === StatusType.OK.toString()) {
                            this.loadData.emit();
                            this.canelEditable.emit();
                        }
                        this.notification.FromStatus(result);
                    });
                break;
            case Dictionary.institutes.toString():
                this.dictionaryService.AddOrUpdateInstitute(this.model).subscribe(
                    result => {
                        this.model = new Institute();
                        if (result.status === StatusType.OK.toString()) {
                            this.loadData.emit();
                            this.canelEditable.emit();
                        }
                        this.notification.FromStatus(result);
                    });
                break;
            case Dictionary.departments.toString():
                this.dictionaryService.AddOrUpdateDepartment(this.model).subscribe(
                    result => {
                        this.model = new Department();
                        if (result.status === StatusType.OK.toString()) {
                            this.loadData.emit();
                            this.canelEditable.emit();
                        }
                        this.notification.FromStatus(result);
                    });
                break;
            case Dictionary.rooms.toString():
                this.dictionaryService.AddOrUpdateRoom(this.model).subscribe(
                    result => {
                        this.model = new Room();
                        if (result.status === StatusType.OK.toString()) {
                            this.loadData.emit();
                            this.canelEditable.emit();
                        }
                        this.notification.FromStatus(result);
                    });
                break;
        }
    }
    changeType() {
      switch (this.type.toString()) {
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

    showMessage(type, text) {
        if (type === 'success')
            this.toastr.success(text, "Успешно");

        if (type === 'error')
            this.toastr.error(text, "Ошибка");
    }

}
