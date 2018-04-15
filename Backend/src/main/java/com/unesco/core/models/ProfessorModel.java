package com.unesco.core.models;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.Professor;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.EntityModel;

import java.util.ArrayList;
import java.util.List;

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
