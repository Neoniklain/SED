package com.unesco.core.dto.shedule;

public class RoomDTO {
    private long id;
    private String room;

    public RoomDTO() {}

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
