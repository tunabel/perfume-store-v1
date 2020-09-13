package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.*;
import com.tunabel.perfumestorev1.model.viewmodel.user.UserVM;

import java.util.List;

public class AdminUserVM {

    private List<UserVM> userVMList;
    private String search;
    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<UserVM> getUserVMList() {
        return userVMList;
    }

    public void setUserVMList(List<UserVM> userVMList) {
        this.userVMList = userVMList;
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
