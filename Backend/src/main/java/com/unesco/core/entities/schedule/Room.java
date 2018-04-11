package com.unesco.core.entities.schedule;

import com.unesco.core.entities.LongId;

import javax.persistence.*;

@Entity
@Table(name = "un_room")
public class Room implements LongId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "room")
    private String room;

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
