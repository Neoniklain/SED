package com.unesco.core.entities.journal;

import com.unesco.core.entities.account.Student;
import com.unesco.core.entities.schedule.Pair;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="un_point")
public class Point {
    @Id
    @SequenceGenerator(name = "pointSequenceGen", sequenceName = "pointSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointSequenceGen")
    private long id;

    private int value;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "point_type_id", referencedColumnName = "id")
    private PointType type;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "pair_id", referencedColumnName = "id")
    private Pair pair;

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

    public PointType getType() {
        return type;
    }
    public void setType(PointType type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public Pair getPair() {
        return pair;
    }
    public void setPair(Pair pair) {
        this.pair = pair;
    }

}
