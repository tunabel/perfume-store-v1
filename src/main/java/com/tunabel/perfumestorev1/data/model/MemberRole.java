package com.tunabel.perfumestorev1.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dbo_member_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "member_role_id")
    private int id;

    @Column(name = "member_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
}
