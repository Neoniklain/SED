package com.unesco.core.dto.shedule;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.plan.SemesterNumberYear;

public class LessonDTO {

    private long id;
    private DisciplineDTO discipline;
    private ProfessorDTO professor;
    private GroupDTO group;
    private SemesterNumberYear semesterNumberYear;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public DisciplineDTO getDiscipline() {
        return discipline;
    }
    public void setDiscipline(DisciplineDTO discipline) {
        this.discipline = discipline;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }
    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public GroupDTO getGroup() {
        return group;
    }
    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public SemesterNumberYear getSemesterNumberYear() {
        return semesterNumberYear;
    }
    public void setSemesterNumberYear(SemesterNumberYear semesterNumberYear) {
        this.semesterNumberYear = semesterNumberYear;
    }
}
