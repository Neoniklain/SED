package com.unesco.core.entities.schedule;


import javax.persistence.*;

@Entity
@Table(name = "un_speciality")
/**
 * Направление обучения
 */
public class SpecialityEntity {
    @Id
    @SequenceGenerator(name = "specialitySequenceGen", sequenceName = "specialitySequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialitySequenceGen")
    private long id;

    @Column(unique=true)
    private String name;

    @Column(unique=true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
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

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public InstituteEntity getInstitute() {
        return institute;
    }
    public void setInstitute(InstituteEntity institute) {
        this.institute = institute;
    }
}
