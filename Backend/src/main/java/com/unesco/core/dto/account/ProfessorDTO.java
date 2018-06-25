package com.unesco.core.dto.account;

import com.unesco.core.dto.plan.DepartmentDTO;

public class ProfessorDTO {

    private long id;
    private DepartmentDTO department;
    private UserDTO user;
    public ProfessorDTO() {
        department = new DepartmentDTO();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
