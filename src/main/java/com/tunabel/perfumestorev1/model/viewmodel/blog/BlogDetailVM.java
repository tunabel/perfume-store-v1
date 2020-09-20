package com.tunabel.perfumestorev1.model.viewmodel.blog;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;

import java.util.Date;
import java.util.List;

public class BlogDetailVM {
    private int id;
    private String title;
    private String shortDesc;
    private String shortImg;
    private String fullDesc;
    private String fullImg;
    private Date createdDate;
    private List<TagVM> blogtagVMList;
    private List<TagVM> alltagVMList;
    private List<BlogVM> recentBlogVMList;
    private HeaderMenuVM headerMenuVM;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<TagVM> getBlogtagVMList() {
        return blogtagVMList;
    }

    public void setBlogtagVMList(List<TagVM> blogtagVMList) {
        this.blogtagVMList = blogtagVMList;
    }

    public List<TagVM> getAlltagVMList() {
        return alltagVMList;
    }

    public void setAlltagVMList(List<TagVM> alltagVMList) {
        this.alltagVMList = alltagVMList;
    }

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }

    public List<BlogVM> getRecentBlogVMList() {
        return recentBlogVMList;
    }

    public void setRecentBlogVMList(List<BlogVM> recentBlogVMList) {
        this.recentBlogVMList = recentBlogVMList;
    }
}
