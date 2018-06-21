package com.unesco.core.entities.account;



import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="un_user")
public class User {
    @Id
    @SequenceGenerator(name = "userSequenceGen", sequenceName = "userSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGen")
    private long id;
    @Column(unique=true)
    @NotNull
    private String username;
    private String userFIO;
    @NotNull
    private String password;
    @NotNull
    @Column(unique=true)
    private String email;
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] photo;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "un_user_role",
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

    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

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