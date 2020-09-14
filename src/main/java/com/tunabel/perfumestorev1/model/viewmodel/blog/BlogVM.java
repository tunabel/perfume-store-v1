package com.tunabel.perfumestorev1.model.viewmodel.blog;

import java.util.Date;
import java.util.List;

public class BlogVM {
    private int id;
    private String title;
    private String shortDesc;
    private String shortImg;
    private String fullDesc;
    private String fullImg;
    private int status;
    private Date createdDate;
    private List<TagVM> tagVMList;

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

    public List<TagVM> getTagVMList() {
        return tagVMList;
    }

    public void setTagVMList(List<TagVM> tagVMList) {
        this.tagVMList = tagVMList;
    }
}
