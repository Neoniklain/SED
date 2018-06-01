package com.unesco.core.entities.schedule;

import com.unesco.core.entities.account.Professor;

import javax.persistence.*;

@Entity
@Table(name = "un_lesson")
public class Lesson {

    @Id
    @SequenceGenerator(name = "lessonSequenceGen", sequenceName = "lessonSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonSequenceGen")
    private long id;
    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private Discipline discipline;
    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Professor professor;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

}
