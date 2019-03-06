import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {DictionaryService} from "../../../services/dictionary.service";
import {PageResult} from "../../../models/admin/PageResult.model.list";
import {Dictionary} from "../../../models/admin/dictionary.model";
import {Group} from "../../../models/shedule/group";
import {ScheduleService} from "../../../services/schedule.service";
import {Lesson} from "../../../models/shedule/lesson";
import {NotificationService} from "../../../services/notification.service";
import {AccountService} from "../../../services/account.service";
import {StudentJournal, StudentJournalList} from "../../../models/journal/journal.model";
import {SemesterNumberYear} from "../../../models/semesterNumberYear.model";

@Component({
    selector: 'students-configurator-page',
    templateUrl: "./studentsConfigurator.html",
    styleUrls: ["./studentsConfigurator.css"],
})

export class StudentsConfiguratorComponent implements OnInit {

    public groups: Group[] = [];
    public groupLessons: Lesson[] = [];
    public studentsJournal: StudentJournal[] = [];
    public selectGroup: Group = new Group();
    public selectLesson: Lesson = new Lesson();
    public semesterNumberYear: SemesterNumberYear = new SemesterNumberYear();

    constructor(private router: Router,
                private scheduleService: ScheduleService,
                private dictionaryService: DictionaryService,
                private notificationService: NotificationService,
                private accountService: AccountService) {
    }

    ngOnInit() {
        this.GetGroups();
    }

    public GetGroups() {
        this.dictionaryService.Get(Dictionary.groups)
            .subscribe((res: PageResult) => {
                this.groups = res.content;
            });
    }

    public selectLessonForGroup() {
        if (this.selectGroup && this.selectGroup.id != 0) {
            this.groupLessons = [];
            this.scheduleService.GetGroupLessons(this.selectGroup.id, this.semesterNumberYear).subscribe(
                result => {
                    this.groupLessons = result.data;
                    this.studentsJournal = [];
                    this.selectLesson = new Lesson();
                }, error => {
                    this.notificationService.Error("Не удалось получить занятия для группы.");
                    console.error(error);
                }
            );
        }
    }

    public selectedLesson(selectLesson) {
        if (selectLesson && selectLesson.id != 0) {
            this.selectLesson = selectLesson;
            this.accountService.GetStudentByGroup(this.selectGroup.id, selectLesson.id).subscribe(
                result => {
                    this.studentsJournal = result.data;
                }, error => {
                    this.notificationService.Error("Не удалось получить студентов для группы.");
                    console.error(error);
                }
            );
        } else {
            this.notificationService.Error("Не верно указано занятие.");
        }
    }

    public save() {
        if (this.studentsJournal && this.studentsJournal.length > 0) {
            let studentJournalList = new StudentJournalList();
            studentJournalList.lesson = this.selectLesson;
            studentJournalList.studentJournal = this.studentsJournal;
            this.accountService.SaveStudentsSubgroup(studentJournalList).subscribe(
                result => {
                    this.notificationService.FromStatus(result);
                }, error => {
                    this.notificationService.Error("Не удалось сохранить подгруппы для студентов.");
                    console.error(error);
                }
            );
        } else {
            this.notificationService.Error("Не выбранны студенты.");
        }
    }

}