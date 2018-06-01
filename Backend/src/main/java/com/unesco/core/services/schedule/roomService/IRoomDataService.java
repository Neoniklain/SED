package com.unesco.core.services.schedule.roomService;

import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.RoomModel;
import com.unesco.core.services.IDataService;

import java.util.List;

public interface IRoomDataService extends IDataService<RoomModel> {
    List<RoomModel> GetPage(FilterQuery filter);
}
