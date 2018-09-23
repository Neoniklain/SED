package com.unesco.core.entities.journal;

import com.unesco.core.entities.account.StudentEntity;
import com.unesco.core.entities.schedule.PairEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="un_point")
public class PointEntity {
    @Id
    @SequenceGenerator(name = "pointSequenceGen", sequenceName = "pointSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointSequenceGen")
    private long id;

    private int value;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "point_type_id", referencedColumnName = "id")
    private PointTypeEntity type;

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "pair_id", referencedColumnName = "id")
    private PairEntity pair;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public PointTypeEntity getType() {
        return type;
    }
    public void setType(PointTypeEntity type) {
        this.type = type;
    }

    public StudentEntity getStudent() {
        return student;
    }
    public void setStudent(StudentEntity studentEntity) {
        this.student = studentEntity;
    }

    public PairEntity getPair() {
        return pair;
    }
    public void setPair(PairEntity pairEntity) {
        this.pair = pairEntity;
    }

}
