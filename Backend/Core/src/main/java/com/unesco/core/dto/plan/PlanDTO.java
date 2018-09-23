package com.unesco.core.dto.plan;

import com.unesco.core.dto.shedule.DisciplineDTO;

import java.util.List;

public class PlanDTO {
    private long id;
    private DisciplineDTO discipline;
    private DepartmentDTO department;
    private String index;
    private List<CompetenceDTO> competence;
    private List<SemesterDTO> semesters;

}
