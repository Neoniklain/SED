package com.unesco.core.dto.journal;

import com.unesco.core.dto.shedule.LessonDTO;

import java.util.List;

public class CertificationReportDTO {
    private LessonDTO lesson;
    private List<CertificationStudentDTO> studentCertification;
    private double allHours;

    public LessonDTO getLesson() {
        return lesson;
    }
    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }

    public List<CertificationStudentDTO> getStudentCertification() {
        return studentCertification;
    }
    public void setStudentCertification(List<CertificationStudentDTO> studentCertification) {
        this.studentCertification = studentCertification;
    }

    public double getAllHours() {
        return allHours;
    }
    public void setAllHours(double allHours) {
        this.allHours = allHours;
    }
}
