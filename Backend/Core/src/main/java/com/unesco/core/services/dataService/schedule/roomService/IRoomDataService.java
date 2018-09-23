package com.unesco.core.services.dataService.schedule.roomService;

import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.shedule.RoomDTO;
import com.unesco.core.services.dataService.IDataService;

public interface IRoomDataService extends IDataService<RoomDTO> {
    PageResultDTO<RoomDTO> getPage(FilterQueryDTO filter);
    RoomDTO getByRoom(String room);
}
