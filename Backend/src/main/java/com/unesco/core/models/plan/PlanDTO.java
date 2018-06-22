package com.unesco.core.models.plan;

import com.unesco.core.models.shedule.DisciplineDTO;

import java.util.List;

public class PlanDTO {
    private long id;
    private DisciplineDTO discipline;
    private DepartmentDTO department;
    private String index;
    private List<CompetenceDTO> competence;
    private List<SemesterDTO> semesters;

}
