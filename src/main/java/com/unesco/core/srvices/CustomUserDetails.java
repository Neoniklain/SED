package com.unesco.core.srvices;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.unesco.core.entities.Role;
import com.unesco.core.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String email;
    private String role;
    private static final long serialVersionUID = 1L;

    public CustomUserDetails(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getRole();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_"+role);
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

    public String getRole() {
        return this.role;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return "vash";
    }
}