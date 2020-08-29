package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dbo_role")
public class Role {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "dbo_member_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> listMember = new HashSet<>();

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<User> getListMember() {
        return this.listMember;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListMember(Set<User> listMember) {
        this.listMember = listMember;
    }
}
