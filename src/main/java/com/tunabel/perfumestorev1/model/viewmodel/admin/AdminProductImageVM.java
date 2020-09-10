package com.tunabel.perfumestorev1.model.viewmodel.admin;


import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuAdminVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductImageVM;

import java.util.List;

public class AdminProductImageVM {

    private HeaderMenuAdminVM headerMenuAdminVM;
    private List<ProductImageVM> productImageVMS;
    private int productId;

    public HeaderMenuAdminVM getHeaderMenuAdminVM() {
        return headerMenuAdminVM;
    }

    public void setHeaderMenuAdminVM(HeaderMenuAdminVM headerMenuAdminVM) {
        this.headerMenuAdminVM = headerMenuAdminVM;
    }

    public List<ProductImageVM> getProductImageVMS() {
        return productImageVMS;
    }

    public void setProductImageVMS(List<ProductImageVM> productImageVMS) {
        this.productImageVMS = productImageVMS;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
