package com.unesco.core.entities;

import com.unesco.core.entities.account.User;

import javax.persistence.*;

@Entity
@Table(name = "un_student")
public class Student {
    @Id
    @SequenceGenerator(name = "studentSequenceGen", sequenceName = "studentSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSequenceGen")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}