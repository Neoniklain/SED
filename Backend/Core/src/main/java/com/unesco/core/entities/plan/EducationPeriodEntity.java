package com.unesco.core.entities.plan;

import com.unesco.core.entities.schedule.SpecialityEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="un_educationPeriod")
public class EducationPeriodEntity {

    @Id
    @SequenceGenerator(name = "educationPeriodSequenceGen", sequenceName = "educationPeriodSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "educationPeriodSequenceGen")
    private long id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private SpecialityEntity speciality;

    @Temporal(TemporalType.DATE)
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

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SpecialityEntity getSpeciality() {
        return speciality;
    }
    public void setSpeciality(SpecialityEntity speciality) {
        this.speciality = speciality;
    }

}
