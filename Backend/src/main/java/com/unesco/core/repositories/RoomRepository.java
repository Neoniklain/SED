package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository <Room, Long> {
    Room findRoomByRoom(String room);
}
