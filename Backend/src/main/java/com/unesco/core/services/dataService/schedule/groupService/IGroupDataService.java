package com.unesco.core.services.dataService.schedule.groupService;

import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IGroupDataService extends IDataService<GroupDTO> {
    List<GroupDTO> getPage(FilterQueryDTO filter);
    GroupDTO getByName(String name);
}
