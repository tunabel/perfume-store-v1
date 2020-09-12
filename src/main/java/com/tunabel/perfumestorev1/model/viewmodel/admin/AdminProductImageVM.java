package com.tunabel.perfumestorev1.model.viewmodel.admin;


import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductImageVM;

import java.util.List;

public class AdminProductImageVM {

    private HeaderMenuAdminVM headerMenuAdminVM;
    private List<ProductImageVM> productImageVMList;
    private int productId;
    private String productNameAndBrand;

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }

    public List<ProductImageVM> getProductImageVMList() {
        return productImageVMList;
    }

    public void setProductImageVMList(List<ProductImageVM> productImageVMList) {
        this.productImageVMList = productImageVMList;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductNameAndBrand() {
        return productNameAndBrand;
    }

    public void setProductNameAndBrand(String productNameAndBrand) {
        this.productNameAndBrand = productNameAndBrand;
    }
}
