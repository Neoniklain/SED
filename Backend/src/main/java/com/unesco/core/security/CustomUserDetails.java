package com.unesco.core.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private long id;
    private String username;
    private String userFIO;
    private String password;
    private String email;
    private List<Role> roles;
    private static final long serialVersionUID = 1L;

    public CustomUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.userFIO = user.getUserFIO();
        this.password = user.getPassword();
        this.email = user.getEmail();
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

    public List<Role> getRole() {
        return this.roles;
    }

    public String getEmail() {
        return email;
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

    public long getId() {
        return id;
    }
}