package com.unesco.core.dto.journal;

import com.unesco.core.dto.account.StudentDTO;

import java.util.List;

public class CertificationStudentDTO {

    /* Студент */
    private StudentDTO student;
    /* Атестация */
    private double value;
    /* Баллы за посещения */
    private double visitationValue;
    /* Пропущено часов */
    private double missingHours;
    /* События */
    private List<CertificationEventDTO> eventValue;

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

    public double getVisitationValue() {
        return visitationValue;
    }
    public void setVisitationValue(double visitationValue) {
        this.visitationValue = visitationValue;
    }

    public List<CertificationEventDTO> getEventValue() {
        return eventValue;
    }
    public void setEventValue(List<CertificationEventDTO> eventValue) {
        this.eventValue = eventValue;
    }
}
