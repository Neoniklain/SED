import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from "@angular/core";
import {Router} from "@angular/router";
import {isUndefined} from "util";
import {Dictionary} from "../../../../models/admin/dictionary.model";
import {DictionaryService} from "../../../../services/dictionary.service";
import {Discipline} from "../../../../models/shedule/discipline";
import {User} from "../../../../models/account/user.model";
import {Group} from "../../../../models/shedule/group";
import {FieldOfKnowledge} from "../../../../models/shedule/fieldOfKnowledge";
import {Institute} from "../../../../models/shedule/institute";
import {Department} from "../../../../models/shedule/department";
import {Room} from "../../../../models/shedule/room.model";
import {StatusType} from "../../../../models/statusType.model";
import {NotificationService} from "../../../../services/notification.service";
import {Speciality} from "../../../../models/shedule/speciality";

@Component({
selector: 'dictionary-table-add',
templateUrl: "./dictionaryTableAdd.component.html",
styleUrls: ["./dictionaryTableAdd.component.css"],
})

export class DictionaryTableAddComponent implements OnInit, OnChanges {

    @Input() type: Dictionary;

    @Input() model;

    @Input() editMode: boolean = false;
    @Input() deleteMode: boolean = false;
    @Output() updateTable = new EventEmitter();
    @Output() canelEditable = new EventEmitter();

    public fieldOfKnowledges: Array<FieldOfKnowledge>;
    public institutes: Array<Institute>;
    public departments: Array<Department>;
    public speciality: Array<Speciality>;
    public Dictionary;
    public columnsName: string[];

    constructor(private notification: NotificationService,
                private router: Router,
                private dictionaryService: DictionaryService) { }

    ngOnInit() {
        this.Dictionary = Dictionary;
        if (this.model != null)
            this.columnsName = Object.keys(this.model);
        else {
            this.updateModel();
            this.columnsName = Object.keys(this.model);
        }
        this.changeType();
    }

    ngOnChanges(changes: SimpleChanges) {
        if (!isUndefined(changes.type)) {
            this.updateModel();
            this.columnsName = Object.keys(this.model);
            this.changeType();
        }
        if (!isUndefined(this.model) && this.model !== null) {
            if (this.deleteMode) {
                this.Delete();
            }
        } else {
            this.updateModel();
        }
    }

    updateModel() {
        this.model = this.dictionaryService.CreateInstance(this.type);
    }

    Add() {
        this.AddOrUpdate();
        this.canelEditable.emit();
    }

    Save() {
       this.AddOrUpdate();
       this.canelEditable.emit();
    }

    Delete() {
        this.dictionaryService.Delete(this.type, this.model.id).subscribe(
            result => {
                this.updateModel();
                if (result.status === StatusType.OK.toString()) {
                    this.updateTable.emit();
                }
                this.notification.FromStatus(result);
                this.canelEditable.emit();
            });
    }

    Cancel() {
        this.canelEditable.emit();
        this.changeType();
    }

    AddOrUpdate() {
        this.dictionaryService.AddOrUpdate(this.type, this.model).subscribe(
            result => {
                this.updateModel();
                if (result.status === StatusType.OK.toString()) {
                    this.updateTable.emit();
                    this.canelEditable.emit();
                }
                this.notification.FromStatus(result);
            });
    }

    changeType() {
        switch (this.type.toString()) {
            case Dictionary.disciplines.toString():
                this.dictionaryService.Get(Dictionary.fieldOfKnowladge).subscribe(result => {
                    this.fieldOfKnowledges = result.content;
                });
                break;
            case Dictionary.departments.toString():
                this.dictionaryService.Get(Dictionary.institutes).subscribe(result => { this.institutes = result.content; });
                break;
            case Dictionary.specialities.toString():
                this.dictionaryService.Get(Dictionary.institutes).subscribe(result => { this.institutes = result.content; });
                this.dictionaryService.Get(Dictionary.departments).subscribe(result => { this.departments = result.content; });
                break;
            case Dictionary.groups.toString():
                this.dictionaryService.Get(Dictionary.specialities).subscribe(result => { this.speciality = result.content; });
                break;
        }
    }

}
