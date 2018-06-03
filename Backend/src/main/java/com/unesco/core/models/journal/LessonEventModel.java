package com.unesco.core.models.journal;

import com.unesco.core.models.shedule.LessonModel;

import java.util.Date;

public class LessonEventModel implements Cloneable {
    private long id;

    private Date date;

    private String comment;

    private PointTypeModel type;

    private LessonModel lesson;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public PointTypeModel getType() {
        return type;
    }
    public void setType(PointTypeModel type) {
        this.type = type;
    }

    public LessonModel getLesson() {
        return lesson;
    }
    public void setLesson(LessonModel lesson) {
        this.lesson = lesson;
    }

    @Override
    public LessonEventModel clone() {
        Object cloned = null;
        try {
            cloned = super.clone();
        } catch (CloneNotSupportedException exc) {
            // В данном примере недостижимо.
        }
        return (LessonEventModel)cloned;
    }

}
