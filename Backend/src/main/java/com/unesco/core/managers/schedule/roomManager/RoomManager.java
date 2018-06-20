package com.unesco.core.managers.schedule.roomManager;

import com.unesco.core.managers.schedule.roomManager.interfaces.room.IRoomManager;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.shedule.RoomModel;
import com.unesco.core.services.schedule.roomService.IRoomDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RoomManager implements IRoomManager {

    @Autowired
    public IRoomDataService dataService;

    RoomModel room;

    public RoomManager() {
        room = new RoomModel();
    }

    public void Init(RoomModel Room) {
        room = Room;
    }

    public RoomModel Get() {
        return room;
    }

    public ResponseStatus Validate() {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatus(StatusTypes.OK);
        if (room.getRoom().equals("")) {
            responseStatus.setStatus(StatusTypes.ERROR);
            responseStatus.addErrors("Не указан номер кабинета");
        }
        return responseStatus;
    }

}
