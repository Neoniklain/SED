package com.unesco.core.services.schedule.groupService;

import com.unesco.core.models.shedule.GroupModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IGroupDataService extends IDataService<GroupModel> {
    List<GroupModel> GetPage(FilterQuery filter);
}
