package com.unesco.core.services.schedule.disciplineService;

import com.unesco.core.models.shedule.DisciplineModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IDisciplineDataService extends IDataService<DisciplineModel> {
    List<DisciplineModel> GetPage(FilterQuery filter);
}
