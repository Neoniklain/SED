package com.unesco.core.security;


import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.entities.account.UserEntity;
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
    private List<RoleEntity> roleEntities;
    private static final long serialVersionUID = 1L;

    public CustomUserDetails(UserEntity userEntity){
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.userFIO = userEntity.getUserFIO();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
        this.photo = userEntity.getPhoto() != null ? new String(userEntity.getPhoto(), StandardCharsets.UTF_8) : "";
        this.roleEntities = new ArrayList<RoleEntity>(userEntity.getRole());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_"+ roleEntities);
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

    public List<RoleEntity> getRole() {
        return this.roleEntities;
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