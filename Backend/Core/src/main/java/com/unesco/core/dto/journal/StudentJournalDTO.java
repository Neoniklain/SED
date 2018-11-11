package com.unesco.core.dto.journal;

import com.unesco.core.dto.account.StudentDTO;

public class StudentJournalDTO {

    private StudentDTO student;

    private int subgroup;

    public StudentDTO getStudent() {
        return student;
    }
    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public int getSubgroup() {
        return subgroup;
    }
    public void setSubgroup(int subgroup) {
        this.subgroup = subgroup;
    }
}
