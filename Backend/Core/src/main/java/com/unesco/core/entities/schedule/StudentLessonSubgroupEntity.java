package com.unesco.core.entities.schedule;

import com.unesco.core.entities.account.StudentEntity;

import javax.persistence.*;

@Entity
@Table(name="un_student_lesson")
public class StudentLessonSubgroupEntity {
    @Id
    @SequenceGenerator(name = "studentLessonSequenceGen", sequenceName = "studentLessonSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentLessonSequenceGen")
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lesson;
    private int subgroup;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return student;
    }
    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public LessonEntity getLesson() {
        return lesson;
    }
    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    public int getSubgroup() {
        return subgroup;
    }
    public void setSubgroup(int subgroup) {
        this.subgroup = subgroup;
    }
}
