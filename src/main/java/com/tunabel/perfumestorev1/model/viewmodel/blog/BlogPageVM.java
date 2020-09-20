package com.tunabel.perfumestorev1.model.viewmodel.blog;

import com.tunabel.perfumestorev1.model.viewmodel.common.*;

import java.util.List;

public class BlogPageVM {
    private List<BlogVM> blogVMList;
    private List<TagVM> tagVMList;
    private HeaderMenuVM headerMenuVM;
    private int page;
    private String sort;
    private int size;

    public BlogPageVM() {
    }

    public List<BlogVM> getBlogVMList() {
        return blogVMList;
    }

    public void setBlogVMList(List<BlogVM> blogVMList) {
        this.blogVMList = blogVMList;
    }

    public List<TagVM> getTagVMList() {
        return tagVMList;
    }

    public void setTagVMList(List<TagVM> tagVMList) {
        this.tagVMList = tagVMList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }
}
