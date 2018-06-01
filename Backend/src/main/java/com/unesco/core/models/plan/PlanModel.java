package com.unesco.core.models.plan;

import com.unesco.core.models.shedule.DisciplineModel;

import java.util.List;

public class PlanModel {
    private long id;
    private DisciplineModel discipline;
    private DepartmentModel department;
    private String index;
    private List<CompetenceModel> competence;
    private List<SemesterModel> semesters;

}
