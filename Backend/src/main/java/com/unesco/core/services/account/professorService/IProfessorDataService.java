package com.unesco.core.services.account.professorService;

import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IProfessorDataService extends IDataService<ProfessorModel> {
    List<ProfessorModel> GetPage(FilterQuery filter);
    ProfessorModel GetByUser(long userId);

    List<ProfessorModel> GetAllByDepartament(long departmentId);
}
