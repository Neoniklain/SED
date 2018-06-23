package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name = "un_room")
public class RoomEntity {
    @Id
    @SequenceGenerator(name = "roomSequenceGen", sequenceName = "roomSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomSequenceGen")
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
