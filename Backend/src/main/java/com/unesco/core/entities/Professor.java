package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "un_professor")
public class Professor implements LongId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "fio")
    private String fio;
    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    private Department department;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
}
