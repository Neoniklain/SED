package com.unesco.core.models.account;

import com.unesco.core.models.plan.DepartmentModel;

public class ProfessorModel {

    private long id;
    private DepartmentModel department;
    private UserModel user;
    public ProfessorModel() {
        department = new DepartmentModel();
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public DepartmentModel getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
