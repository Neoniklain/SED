package com.unesco.core.ViewModel;

import com.unesco.core.entities.Role;
import com.unesco.core.srvices.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel {

    public String username;
    public String email;
    public List<RoleViewModel> roles;

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

    public List<RoleViewModel> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleViewModel> roles) {
        this.roles = roles;
    }

    public UserViewModel(CustomUserDetails u) {
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.roles = new ArrayList<RoleViewModel>();
        for (Role role: u.getRole()) {
            this.roles.add(new RoleViewModel(role.getRoleName()));
        }
    }
}
