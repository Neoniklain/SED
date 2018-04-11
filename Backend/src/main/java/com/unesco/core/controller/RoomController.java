package com.unesco.core.controller;

import com.unesco.core.entities.schedule.Room;
import com.unesco.core.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/demo")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping("/rooms")
    public List<Room> getDisciplines() {
        Iterable<Room> tmp = roomRepository.findAll();
        return StreamSupport.stream(tmp.spliterator(), false).collect(Collectors.toList());
    }
}
