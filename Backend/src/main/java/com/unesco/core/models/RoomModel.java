package com.unesco.core.models;

public class RoomModel {
    private long id;
    private String room;

    public RoomModel() {}

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
