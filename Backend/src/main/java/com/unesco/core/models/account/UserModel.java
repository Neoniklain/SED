package com.unesco.core.models.account;

import com.unesco.core.entities.account.Role;
import com.unesco.core.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private long id;
    private String username;
    private String password;
    private String userFIO;
    private String email;
    private String photo;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }


    public UserModel(CustomUserDetails u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.userFIO = u.getUserFIO();
        this.password = u.getPassword();
        this.email = u.getEmail();
        this.photo = u.getPhoto();
        this.roles = new ArrayList<RoleModel>();
        for (Role role: u.getRole()) {
            RoleModel roleModel = new RoleModel();
            roleModel.setRoleName(role.getRoleName());
            this.roles.add(roleModel);
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
