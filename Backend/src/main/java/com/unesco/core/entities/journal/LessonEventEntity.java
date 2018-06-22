package com.unesco.core.entities.journal;

import com.unesco.core.entities.schedule.LessonEntity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="un_lesson_event")
public class LessonEventEntity {
    @Id
    @SequenceGenerator(name = "lessonEventSequenceGen", sequenceName = "lessonEventSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonEventSequenceGen")
    private long id;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    private Timestamp date;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "point_type_id", referencedColumnName = "id")
    private PointTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lessonEntity;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public PointTypeEntity getType() {
        return type;
    }
    public void setType(PointTypeEntity type) {
        this.type = type;
    }

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }
    public void setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
    }

}