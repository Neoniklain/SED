package com.unesco.core.entities.account;

import com.unesco.core.entities.schedule.DepartmentEntity;

import javax.persistence.*;

@Entity
@Table(name = "un_professor")
public class ProfessorEntity {

    @Id
    @SequenceGenerator(name = "professorSequenceGen", sequenceName = "professorSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professorSequenceGen")
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentEntity departmentEntity) {
        this.department = departmentEntity;
    }
}
