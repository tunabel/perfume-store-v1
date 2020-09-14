package com.tunabel.perfumestorev1.model.viewmodel.blog;

import java.util.Date;

public class TagVM {
    private int id;
    private String name;
    private int blogCount;

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

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
