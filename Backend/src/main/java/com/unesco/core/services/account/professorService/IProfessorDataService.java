package com.unesco.core.services.account.professorService;

import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IProfessorDataService extends IDataService<ProfessorDTO> {
    List<ProfessorDTO> GetPage(FilterQueryDTO filter);
    ProfessorDTO GetByUser(long userId);

    List<ProfessorDTO> GetAllByDepartament(long departmentId);
}
