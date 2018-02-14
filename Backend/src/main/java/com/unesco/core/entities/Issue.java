package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="issue")
public class Issue {
    @Id
    @SequenceGenerator(name = "issueSequenceGen", sequenceName = "issueSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issueSequenceGen")
    private long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User creator;
    @ManyToMany
    @JoinTable(name = "issue_collaborators",
            joinColumns = {@JoinColumn(name = "issue_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> collaborators;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }
    public Set<User> getCollaborators() {
        return collaborators;
    }
    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }

}
