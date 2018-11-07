package com.unesco.core.entities.journal;

import com.unesco.core.entities.schedule.LessonEntity;
import com.unesco.core.entities.schedule.PairEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


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

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "un_lesson_event_pair",
            joinColumns = {@JoinColumn(name = "lessons_event_id")},
            inverseJoinColumns = {@JoinColumn(name = "pair_id")})
    private Set<PairEntity> pairs;

    private int maxValue;

    @ManyToOne
    @JoinColumn(name = "point_type_id", referencedColumnName = "id")
    private PointTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lessonEntity;

    public Set<PairEntity> getPairs() {
        return pairs;
    }
    public void setPairs(Set<PairEntity> pairs) {
        this.pairs = pairs;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
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

    public LessonEntity getLesson() {
        return lessonEntity;
    }
    public void setLesson(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
    }

}