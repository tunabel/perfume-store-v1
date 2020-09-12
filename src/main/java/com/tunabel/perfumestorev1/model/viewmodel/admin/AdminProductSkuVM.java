package com.tunabel.perfumestorev1.model.viewmodel.admin;

import com.tunabel.perfumestorev1.model.viewmodel.common.*;

import java.util.Date;
import java.util.List;

public class AdminProductSkuVM {

    private List<ProductSkuVM> productSkuVMList;
    private String productNameAndBrand;

    public String getProductNameAndBrand() {
        return productNameAndBrand;
    }

    public void setProductNameAndBrand(String productNameAndBrand) {
        this.productNameAndBrand = productNameAndBrand;
    }

    private HeaderMenuAdminVM headerMenuAdminVM;

    public List<ProductSkuVM> getProductSkuVMList() {
        return productSkuVMList;
    }

    public void setProductSkuVMList(List<ProductSkuVM> productSkuVMList) {
        this.productSkuVMList = productSkuVMList;
    }

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }
}
