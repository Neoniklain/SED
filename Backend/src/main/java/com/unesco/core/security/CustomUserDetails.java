package com.unesco.core.security;


import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private long id;
    private String username;
    private String userFIO;
    private String password;
    private String email;
    private String photo;
    private List<Role> roles;
    private static final long serialVersionUID = 1L;

    public CustomUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.userFIO = user.getUserFIO();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.photo = new String(user.getPhoto(), StandardCharsets.UTF_8);
        this.roles = new ArrayList<Role>(user.getRoles());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_"+roles);
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return this.id;
    }

    public List<Role> getRole() {
        return this.roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getUsername() {
        return username;
    }

    public String getUserFIO() {
        return userFIO;
    }

    public String getPassword() {
        return this.password;
    }

}