package com.unesco.core.services.dataService.plan.educationPeriodService;

import com.unesco.core.dto.plan.EducationPeriodDTO;
import com.unesco.core.services.dataService.IDataService;

public interface IEducationPeriodService extends IDataService<EducationPeriodDTO> {
    EducationPeriodDTO getEducationPeriodForYearAndSemester(int semester, int year);

    EducationPeriodDTO getEducationPeriodForGroup(long groupId, int semester, int year);
}
