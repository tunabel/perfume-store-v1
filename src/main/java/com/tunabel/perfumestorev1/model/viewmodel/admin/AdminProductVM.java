package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.*;

import java.util.List;

public class AdminProductVM {

    private List<ProductVM> productVMList;
    private List<BrandVM> brandVMList;
    private List<ScentVM> scentVMList;
    private List<TypeVM> typeVMList;
    private String search;
    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<ProductVM> getProductVMList() {
        return productVMList;
    }

    public void setProductVMList(List<ProductVM> productVMList) {
        this.productVMList = productVMList;
    }

    public List<BrandVM> getBrandVMList() {
        return brandVMList;
    }

    public void setBrandVMList(List<BrandVM> brandVMList) {
        this.brandVMList = brandVMList;
    }

    public List<ScentVM> getScentVMList() {
        return scentVMList;
    }

    public void setScentVMList(List<ScentVM> scentVMList) {
        this.scentVMList = scentVMList;
    }

    public List<TypeVM> getTypeVMList() {
        return typeVMList;
    }

    public void setTypeVMList(List<TypeVM> typeVMList) {
        this.typeVMList = typeVMList;
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
