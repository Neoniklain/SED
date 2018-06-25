package com.unesco.core.entities.account;

import com.unesco.core.entities.schedule.GroupEntity;

import javax.persistence.*;

@Entity
@Table(name = "un_student")
public class StudentEntity {
    @Id
    @SequenceGenerator(name = "studentSequenceGen", sequenceName = "studentSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSequenceGen")
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity group;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }

    public GroupEntity getGroup() {
        return group;
    }
    public void setGroup(GroupEntity groupEntity) {
        this.group = groupEntity;
    }
}