package com.unesco.core.models.shedule;

import java.util.List;

public class DepartmentSheduleDTO {


    private List<SheduleProfessorLineDTO> lines;

    public List<SheduleProfessorLineDTO> getLines() {
        return lines;
    }
    public void setLines(List<SheduleProfessorLineDTO> lines) {
        this.lines = lines;
    }

    public DepartmentSheduleDTO() {
    }
}

