package com.unesco.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority {
    @Id
    @SequenceGenerator(name = "roleSequenceGen", sequenceName = "roleSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequenceGen")
    private long id;
    private String role;
    private String roleNameRus;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return role;
    }
    public void setRoleName(String role) {
        this.role = role;
    }

    public String getRoleNameRus() { return roleNameRus; }
    public void setRoleNameRus(String roleNameRus) {
        this.roleNameRus = roleNameRus;
    }

    protected Role(){}
    public Role(String name, String roleName)
    {
        role = name;
        roleNameRus = roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}

