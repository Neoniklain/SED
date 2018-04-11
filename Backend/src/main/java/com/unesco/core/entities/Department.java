package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "un_department")
/**
 * Класс кафедры
 */
public class Department implements LongId {

    @Id
    @SequenceGenerator(name = "departmentSequenceGen", sequenceName = "departmentSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSequenceGen")
    private long id;
    /**
     * Поле название
     */
    private String name;
    @ManyToOne
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    /** Поле Институты */
    private Institute institute;

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

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }



    public Department() {
    }
}
