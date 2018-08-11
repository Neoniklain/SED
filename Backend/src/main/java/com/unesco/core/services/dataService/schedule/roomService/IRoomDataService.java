package com.unesco.core.services.dataService.schedule.roomService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.shedule.RoomDTO;
import com.unesco.core.services.dataService.IDataService;

import java.util.List;

public interface IRoomDataService extends IDataService<RoomDTO> {
    List<RoomDTO> getPage(FilterQueryDTO filter);
    RoomDTO getByRoom(String room);
}
