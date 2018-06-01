package com.unesco.core.models.shedule;

import java.util.List;

public class DepartmentSheduleModel {


    private List<SheduleProfessorLineModel> lines;

    public List<SheduleProfessorLineModel> getLines() {
        return lines;
    }
    public void setLines(List<SheduleProfessorLineModel> lines) {
        this.lines = lines;
    }

    public DepartmentSheduleModel() {
    }
}

