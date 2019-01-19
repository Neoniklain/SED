import {Component, OnInit} from "@angular/core";
import {AuthenticationService} from "../../../services/auth.service";
import {UserCreate} from "../../../models/account/user.model";
import {Role} from "../../../models/account/role.model";
import {DictionaryService} from "../../../services/dictionary.service";
import {UtilsService} from "../../../services/utils.service";
import {Group} from "../../../models/shedule/group";
import {Department} from "../../../models/shedule/department";
import {Roles} from "../../../models/account/role.model";
import {AccountService} from "../../../services/account.service";
import {StatusType} from "../../../models/statusType.model";
import {NotificationService} from "../../../services/notification.service";
import {Dictionary} from "../../../models/admin/dictionary.model";

@Component({
    selector: 'user-add',
    templateUrl: "./userAdd.html",
    styleUrls: ["./userAdd.css"]
})

export class UserAddComponent implements OnInit {

    public model: UserCreate;
    public currentDepartment: Department = new Department();
    public currentGroup: Group = new Group();

    public roles: Array<Role>;
    public findRoles: Array<Role>;
    public departments: Array<Department> = new Array<Department>();
    public groups: Array<Group> = new Array<Group>();
    public Roles;

    constructor(private notification: NotificationService,
                private dictionaryService: DictionaryService,
                private accountService: AccountService,
                private utilsService: UtilsService,
                private authenticationService: AuthenticationService) {}

    ngOnInit() {
        this.Roles = Roles;
        this.model = new UserCreate();
        this.dictionaryService.Get(Dictionary.roles).subscribe(result => { this.roles = result.content; });
    }

    AddOrUpdate() {
        this.authenticationService.register(this.model).subscribe( result => {
                if (result.status === StatusType.OK.toString()) {
                    if (this.isRole(Roles.Professor)) {
                        this.accountService.setProfessorDepartment(result.data.id, this.currentDepartment.id)
                            .subscribe( resultP => {
                                    this.notification.FromStatus(resultP);
                        });
                    }
                    if (this.isRole(Roles.Student)) {
                        this.accountService.setStudentGroup(result.data.id, this.currentGroup.id)
                            .subscribe( resultS => {
                                    this.notification.FromStatus(resultS);
                        });
                    }
                    this.model = new UserCreate();
                }
                this.notification.FromStatus(result);
            });
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
        else
            this.findRoles = [];
    }

    setRole() {
        if (this.model.roles.find(x => x.roleName === Roles.Professor.toString())) {
            this.dictionaryService.Get(Dictionary.departments).subscribe(result => {
                this.departments = result.content; });
        }
        if (this.model.roles.find(x => x.roleName === Roles.Student.toString())) {
            this.dictionaryService.Get(Dictionary.groups).subscribe(result => {
                this.groups = result.content; });
        }
    }

    isRole(role) {
        if (this.model.roles.find(x => x.roleName === role)) {
            return true;
        }
        return false;
    }

    createRandomUser() {
        if (this.model.userFIO && this.model.userFIO != "") {
            this.model.username = this.utilsService.getNickname(this.model.userFIO);
            this.model.email = this.model.username + "@mail.ru";
            this.model.password = this.utilsService.generatePassword(8);
        }
    }
}