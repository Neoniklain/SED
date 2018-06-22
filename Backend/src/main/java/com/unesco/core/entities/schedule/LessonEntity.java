package com.unesco.core.entities.schedule;

import com.unesco.core.entities.account.ProfessorEntity;

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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public DisciplineEntity getDisciplineEntity() {
        return discipline;
    }
    public void setDisciplineEntity(DisciplineEntity discipline) {
        this.discipline = discipline;
    }

    public ProfessorEntity getProfessorEntity() {
        return professor;
    }
    public void setProfessorEntity(ProfessorEntity professor) {
        this.professor = professor;
    }

    public GroupEntity getGroupEntity() {
        return group;
    }
    public void setGroupEntity(GroupEntity group) {
        this.group = group;
    }

}
