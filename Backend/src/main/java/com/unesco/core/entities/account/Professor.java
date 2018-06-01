package com.unesco.core.entities.account;

import com.unesco.core.entities.schedule.Department;

import javax.persistence.*;

@Entity
@Table(name = "un_professor")
public class Professor {

    @Id
    @SequenceGenerator(name = "professorSequenceGen", sequenceName = "professorSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professorSequenceGen")
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
}
