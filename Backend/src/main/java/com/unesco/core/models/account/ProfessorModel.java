package com.unesco.core.models.account;

import com.unesco.core.models.plan.DepartmentModel;

public class ProfessorModel extends UserModel {

    public DepartmentModel department;

    public ProfessorModel() {
        department = new DepartmentModel();
    }

    public DepartmentModel getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }
}
