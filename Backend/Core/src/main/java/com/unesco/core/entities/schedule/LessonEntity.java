package com.unesco.core.entities.schedule;

import com.unesco.core.entities.account.ProfessorEntity;
import com.unesco.core.entities.plan.EducationPeriodEntity;

import javax.persistence.*;

@Entity
@Table(name = "un_lesson")
public class LessonEntity {

    @Id
    @SequenceGenerator(name = "lessonSequenceGen", sequenceName = "lessonSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonSequenceGen")
    private long id;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private DisciplineEntity discipline;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private ProfessorEntity professor;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "educationPeriod_id", referencedColumnName = "id")
    private EducationPeriodEntity educationPeriod;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public DisciplineEntity getDiscipline() {
        return discipline;
    }
    public void setDiscipline(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    public ProfessorEntity getProfessor() {
        return professor;
    }
    public void setProfessor(ProfessorEntity professor) {
        this.professor = professor;
    }

    public GroupEntity getGroup() {
        return group;
    }
    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public EducationPeriodEntity getEducationPeriod() {
        return educationPeriod;
    }
    public void setEducationPeriod(EducationPeriodEntity educationPeriod) {
        this.educationPeriod = educationPeriod;
    }
}
