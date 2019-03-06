package com.unesco.core.dto.plan;

import com.unesco.core.dto.shedule.SpecialityDTO;

import java.util.Date;


public class EducationPeriodDTO {
    private long id;
    private Date startDate;
    private SpecialityDTO speciality;
    private Date endDate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }
    public void setSpeciality(SpecialityDTO speciality) {
        this.speciality = speciality;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
