package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.blog.BlogVM;
import com.tunabel.perfumestorev1.model.viewmodel.blog.TagVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.BrandVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;

import java.util.List;

public class AdminBlogVM {

    private List<BlogVM> blogVMList;
    private List<TagVM> tagVMList;
    private String search;
    private HeaderMenuAdminVM headerMenuAdminVM;

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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }
}
