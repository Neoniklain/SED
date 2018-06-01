package com.unesco.core.managers.schedule.roomManager;

import com.unesco.core.managers.schedule.roomManager.interfaces.roomList.IRoomListManager;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.shedule.RoomModel;
import com.unesco.core.services.schedule.roomService.IRoomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class RoomListManager implements IRoomListManager {

    @Autowired
    public IRoomDataService dataService;

    public List<RoomModel> roomList;

    public RoomListManager() {
        roomList = new ArrayList<>();
    }

    public void Init(List<RoomModel> RoomList) {
        roomList = RoomList;
    }

    public List<RoomModel> GetAll() {
        return roomList;
    }
}
