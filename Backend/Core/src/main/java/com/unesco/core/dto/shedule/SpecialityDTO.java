package com.unesco.core.dto.shedule;

import com.unesco.core.dto.plan.DepartmentDTO;

public class SpecialityDTO {
    private long id;
    private String name;
    private DepartmentDTO department;

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

    public DepartmentDTO getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
}

