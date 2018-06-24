package com.unesco.core.services.schedule.roomService;

import com.unesco.core.models.additional.FilterQueryDTO;
import com.unesco.core.models.shedule.RoomDTO;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IRoomDataService extends IDataService<RoomDTO> {
    List<RoomDTO> GetPage(FilterQueryDTO filter);
    RoomDTO GetByRoom(String room);
}
