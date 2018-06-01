package com.unesco.core.models.shedule;

import com.unesco.core.models.account.ProfessorModel;

public class LessonModel {

    private long id;
    private DisciplineModel discipline;
    private ProfessorModel professor;
    private GroupModel group;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public DisciplineModel getDiscipline() {
        return discipline;
    }
    public void setDiscipline(DisciplineModel discipline) {
        this.discipline = discipline;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }
    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public GroupModel getGroup() {
        return group;
    }
    public void setGroup(GroupModel group) {
        this.group = group;
    }

}
