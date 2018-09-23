package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name = "un_department")
/**
 * Класс кафедры
 */
public class DepartmentEntity {

    @Id
    @SequenceGenerator(name = "departmentSequenceGen", sequenceName = "departmentSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSequenceGen")
    private long id;
    /**
     * Поле название
     */
    @Column(unique=true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    /** Поле Институты */
    private InstituteEntity institute;

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

    public InstituteEntity getInstitute() {
        return institute;
    }

    public void setInstitute(InstituteEntity instituteEntity) {
        this.institute = instituteEntity;
    }



    public DepartmentEntity() {
    }
}
