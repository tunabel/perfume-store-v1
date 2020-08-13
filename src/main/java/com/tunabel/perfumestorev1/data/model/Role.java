package com.tunabel.perfumestorev1.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
            inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> listMember = new HashSet<>();

}
