package com.unesco.core.services.schedule.instituteService;

import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.InstituteModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IInstituteDataService extends IDataService<InstituteModel> {
    List<InstituteModel> GetPage(FilterQuery filter);
}
