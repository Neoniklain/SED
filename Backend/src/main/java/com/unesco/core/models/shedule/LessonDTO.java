package com.unesco.core.models.shedule;

import com.unesco.core.models.account.ProfessorDTO;

public class LessonDTO {

    private long id;
    private DisciplineDTO discipline;
    private ProfessorDTO professor;
    private GroupDTO group;

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

}
