package com.tunabel.perfumestorev1.data.model.blog;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    int id;

    @NotNull
    String title;

    @NotNull
    @Column(name = "short_desc")
    String shortDesc;

    @NotNull
    @Column(name = "short_img")
    String shortImg;

    @NotNull
    @Column(name = "full_desc")
    String fullDesc;

    @NotNull
    @Column(name = "full_img")
    String fullImg;

    @NotNull
    int status;

    @NotNull
    @Column(name = "created_date")
    Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
    List<BlogTag> blogTagList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortImg() {
        return shortImg;
    }

    public void setShortImg(String shortImg) {
        this.shortImg = shortImg;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public String getFullImg() {
        return fullImg;
    }

    public void setFullImg(String fullImg) {
        this.fullImg = fullImg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<BlogTag> getBlogTagList() {
        return blogTagList;
    }

    public void setBlogTagList(List<BlogTag> blogTagList) {
        this.blogTagList = blogTagList;
    }
}
