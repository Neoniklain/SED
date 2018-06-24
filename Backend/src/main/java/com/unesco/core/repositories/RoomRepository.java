package com.unesco.core.repositories;

import com.unesco.core.entities.schedule.RoomEntity;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository <RoomEntity, Long>, CrudPagableRepository<RoomEntity, Long> {
    RoomEntity findRoomByRoom(String room);

    @Query("SELECT r FROM RoomEntity r where lower(r.room) LIKE CONCAT('%',lower(:filter),'%')")
    List<RoomEntity> findWithFilter(Pageable pageable, @Param("filter")  String filter);

    RoomEntity findByRoom(String name);
}
