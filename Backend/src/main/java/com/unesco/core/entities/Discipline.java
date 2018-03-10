package com.unesco.core.entities;

import com.unesco.core.entities.plan.Plan;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="un_discipline")
/**
 * Класс предметов дисциплин (модулей)
 */
public class Discipline {
    @Id
    @SequenceGenerator(name = "disciplineSequenceGen", sequenceName = "disciplineSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplineSequenceGen")
    private long id;
    /** Поле название */
    private String name;
    /** Поле дата создания */
    private Date datecreate;
    @ManyToOne
    @JoinColumn(name = "fieldOfKnowledge_id", referencedColumnName = "id")
    /** Поле раздел знаний */
    private FieldOfKnowledge fieldOfKnowledge;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDatecreate() {
        return datecreate;
    }
    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public FieldOfKnowledge getFieldOfKnowledge() {
        return fieldOfKnowledge;
    }
    public void setFieldOfKnowledge(FieldOfKnowledge fieldOfKnowledge) {
        this.fieldOfKnowledge = fieldOfKnowledge;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discipline")
    /** Поле набор планов */
    private Set<Plan> plan;

    public Set<Plan> getPlan() {
        return plan;
    }
    public void setPlan(Set<Plan> plan) {
        this.plan = plan;
    }

    public Discipline(){}
    public Discipline(String name, Date datecreate)
    {
        this.name = name;
        this.datecreate = datecreate;
    }
}
