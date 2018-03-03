package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    public String username;
    public String email;
    public List<RoleModel> roles;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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

    public UserModel(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = new ArrayList<>();
        for (Role r: user.getRoles()) {
            this.roles.add(new RoleModel(r.getRoleName()));
        }
    }

    public UserModel(CustomUserDetails u) {
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.roles = new ArrayList<RoleModel>();
        for (Role role: u.getRole()) {
            this.roles.add(new RoleModel(role.getRoleName()));
        }
    }
}
