package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private long id;
    private String username;
    private String userFIO;
    private String email;
    private List<RoleModel> roles;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFIO() {
        return userFIO;
    }
    public void setUserFIO(String userFIO) {
        this.userFIO = userFIO;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = new ArrayList<>();
        this.userFIO = user.getUserFIO();
        for (Role r: user.getRoles()) {
            this.roles.add(new RoleModel(r));
        }
    }

    public UserModel(CustomUserDetails u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.userFIO = u.getUserFIO();
        this.email = u.getEmail();
        this.roles = new ArrayList<RoleModel>();
        for (Role role: u.getRole()) {
            this.roles.add(new RoleModel(role));
        }
    }

    public UserModel()
    {
        this.id = 0;
        this.roles = new ArrayList<>();
        this.email = "";
        this.username = "";
        this.userFIO = "";
    }
}
