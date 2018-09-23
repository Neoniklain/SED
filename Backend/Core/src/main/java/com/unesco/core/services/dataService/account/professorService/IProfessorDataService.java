package com.unesco.core.services.dataService.account.professorService;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IProfessorDataService extends IDataService<ProfessorDTO> {
    PageResultDTO<ProfessorDTO> getPage(FilterQueryDTO filter);
    ProfessorDTO getByUser(long userId);

    List<ProfessorDTO> getAllByDepartament(long departmentId);
}
