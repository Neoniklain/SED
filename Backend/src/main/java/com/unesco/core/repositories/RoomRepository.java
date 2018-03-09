package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository <Room, Integer> {
    Room findRoomByRoom(String room);
}
