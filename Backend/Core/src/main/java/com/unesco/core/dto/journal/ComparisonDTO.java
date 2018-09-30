package com.unesco.core.dto.journal;

import com.unesco.core.dto.shedule.PairDTO;

import java.util.Date;
import java.util.List;

public class ComparisonDTO {
    private Date date;
    private List<ComparisonPointDTO> points;

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public List<ComparisonPointDTO> getPoints() {
        return points;
    }
    public void setPoints(List<ComparisonPointDTO> points) {
        this.points = points;
    }
}
