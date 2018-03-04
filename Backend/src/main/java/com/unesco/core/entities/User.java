package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @SequenceGenerator(name = "userSequenceGen", sequenceName = "userSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGen")
    private long id;
    @Column(unique=true)
    private String username;
    private String userFIO;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

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

    public String getUserFIO() { return userFIO; }
    public void setUserFIO(String userFIO) { this.userFIO = userFIO;  }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(){
        this.username = "";
        this.password = "";
        this.email = "";
        this.userFIO = "";
    }
    public User(String name, String FIO, String email, String pass ) {
        this.username = name;
        this.password = pass;
        this.email = email;
        this.userFIO = FIO;
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.userFIO = user.userFIO;
    }
}