package com.unesco.core.managers.schedule.roomManager;

import com.unesco.core.managers.schedule.roomManager.interfaces.room.IRoomManager;
import com.unesco.core.models.additional.ResponseStatusDTO;
import com.unesco.core.models.shedule.RoomDTO;
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

    RoomDTO room;

    public RoomManager() {
        room = new RoomDTO();
    }

    public void Init(RoomDTO Room) {
        room = Room;
    }

    public RoomDTO Get() {
        return room;
    }

    public ResponseStatusDTO Validate() {
        ResponseStatusDTO responseStatusDTO = new ResponseStatusDTO();
        responseStatusDTO.setStatus(StatusTypes.OK);
        if (room.getRoom().equals("")) {
            responseStatusDTO.setStatus(StatusTypes.ERROR);
            responseStatusDTO.addErrors("Не указан номер кабинета");
        }
        return responseStatusDTO;
    }

}
