package com.unesco.core.entities.workflow;

import com.unesco.core.entities.LongId;
import com.unesco.core.entities.account.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_issue")
public class Issue implements LongId {
    @Id
    @SequenceGenerator(name = "issueSequenceGen", sequenceName = "issueSequenceGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issueSequenceGen")
    private long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User creator;
    @ManyToMany
    @JoinTable(name = "un_issue_collaborator",
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
