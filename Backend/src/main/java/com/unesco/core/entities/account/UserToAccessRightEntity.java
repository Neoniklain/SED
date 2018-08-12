package com.unesco.core.entities.account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="un_user_access_right")
public class UserToAccessRightEntity {

    @Id
    @SequenceGenerator(name = "user_accessRightSequenceGen", sequenceName = "user_accessRightSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accessRightSequenceGen")
    private long id;

    @OneToOne
    @NotNull
    @JoinColumn(name = "access_right_id")
    private AccessRightEntity right;

    @OneToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @NotNull
    private boolean allow;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public AccessRightEntity getRight() {
        return right;
    }
    public void setRight(AccessRightEntity right) {
        this.right = right;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isAllow() {
        return allow;
    }
    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
