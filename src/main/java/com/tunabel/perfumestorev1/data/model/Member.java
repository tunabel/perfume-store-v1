package com.tunabel.perfumestorev1.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dbo_member")
@Getter
@Setter
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    @Id
    private int id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String name;

    private Date birthday;

    private int gender;

    private String address;

    private String phone;

    @Column(name = "avatar_url")
    private String avatarURL;

    private int status;

    @Column(name = "created_date")
    private Date createdDate;

}
