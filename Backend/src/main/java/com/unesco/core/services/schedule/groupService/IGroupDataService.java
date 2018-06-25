package com.unesco.core.services.schedule.groupService;

import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IGroupDataService extends IDataService<GroupDTO> {
    List<GroupDTO> GetPage(FilterQueryDTO filter);
    GroupDTO GetByName(String name);
}
