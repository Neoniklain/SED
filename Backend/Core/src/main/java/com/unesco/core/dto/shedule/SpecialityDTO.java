package com.unesco.core.dto.shedule;

import com.unesco.core.dto.plan.DepartmentDTO;

public class SpecialityDTO {
    private long id;
    private String name;
    private String code;
    private DepartmentDTO department;
    private InstituteDTO institute;

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

    public DepartmentDTO getDepartment() {
        return department;
    }
    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public InstituteDTO getInstitute() {
        return institute;
    }
    public void setInstitute(InstituteDTO institute) {
        this.institute = institute;
    }
}

