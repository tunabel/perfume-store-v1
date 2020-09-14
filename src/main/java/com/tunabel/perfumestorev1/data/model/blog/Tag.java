package com.tunabel.perfumestorev1.data.model.blog;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dbo_tag")
public class Tag {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull
    @Column(name = "tag_name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
