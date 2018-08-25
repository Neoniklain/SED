package com.unesco.core.entities.schedule;

import com.unesco.core.entities.plan.PlanEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_discipline")
/**
 * Класс предметов дисциплин (модулей)
 */
public class DisciplineEntity {
    @Id
    @SequenceGenerator(name = "disciplineSequenceGen", sequenceName = "disciplineSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplineSequenceGen")
    private long id;
    /** Поле название */
    @Column(unique=true)
    private String name; //для каждого раздела свои контроллеры: для группы - в GroupContr, для преподов - в ProfessorContr, и так далее. :******
    @ManyToOne
    @JoinColumn(name = "fieldOfKnowledge_id", referencedColumnName = "id")
    /** Поле раздел знаний */
    private FieldOfKnowledgeEntity fieldOfKnowledge;

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

    public FieldOfKnowledgeEntity getFieldOfKnowledge() {
        return fieldOfKnowledge;
    }
    public void setFieldOfKnowledge(FieldOfKnowledgeEntity fieldOfKnowledge) {
        this.fieldOfKnowledge = fieldOfKnowledge;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discipline")
    /** Поле набор планов */
    private Set<PlanEntity> plan;

    public Set<PlanEntity> getPlan() {
        return plan;
    }
    public void setPlan(Set<PlanEntity> plan) {
        this.plan = plan;
    }

    public DisciplineEntity(){}
    public DisciplineEntity(String name)
    {
        this.name = name;
    }
}
