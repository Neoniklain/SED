package com.unesco.core.dto.journal;

import com.unesco.core.dto.account.StudentDTO;

public class CertificationStudentDto {

    /* Студент */
    private StudentDTO student;
    /* Атестация */
    private double value;
    /* Пропущено часов */
    private double missingHours;

    public StudentDTO getStudent() {
        return student;
    }
    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public double getMissingHours() {
        return missingHours;
    }
    public void setMissingHours(double missingHours) {
        this.missingHours = missingHours;
    }

}
