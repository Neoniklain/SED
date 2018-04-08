package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private long id;
    private String username;
    private String userFIO;
    private String email;
    private List<RoleModel> roles;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

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

    public UserModel() {
        this.id = 0;
        this.username = "";
        this.userFIO = "";
        this.email = "";
        this.roles = new ArrayList<RoleModel>();
    }
    public UserModel(CustomUserDetails u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.userFIO = u.getUserFIO();
        this.email = u.getEmail();
        this.roles = new ArrayList<RoleModel>();
        for (Role role: u.getRole()) {
            RoleModel roleModel = new RoleModel();
            roleModel.setRoleName(role.getRoleName());
            this.roles.add(roleModel);
        }
    }
}
