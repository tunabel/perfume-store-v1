package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.BrandVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;

import java.util.List;

public class AdminBrandVM {

    private List<BrandVM> brandVMList;
    private String search;
    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<BrandVM> getBrandVMList() {
        return brandVMList;
    }

    public void setBrandVMList(List<BrandVM> brandVMList) {
        this.brandVMList = brandVMList;
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
