package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.Group;
import com.unesco.core.entities.schedule.Room;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository <Room, Long>, CrudPagableRepository<Room, Long> {
    Room findRoomByRoom(String room);

    @Query("SELECT r FROM Room r where lower(r.room) LIKE CONCAT('%',lower(:filter),'%')")
    List<Room> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    Group findByRoom(String name);
}
