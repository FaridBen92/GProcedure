package fr.faridBenjomaa.GProcedure.Security.Entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name= "GROUPS")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String role;
    @OneToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<User> users;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return id == groups.id &&
                Objects.equals(name, groups.name) &&
                Objects.equals(role, groups.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

}
