package com.tunabel.perfumestorev1.model.viewmodel.shop;

import com.tunabel.perfumestorev1.model.viewmodel.common.BrandVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ScentVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.TypeVM;

import java.util.List;

public class ShopVM {
    private List<ProductSkuVM> productSkuVMList;
    private List<BrandVM> brandVMList;
    private List<TypeVM> typeVMList;
    private List<ScentVM> scentVMList;
    private int page;
    private String sort;
    private int size;

    public ShopVM(List<ProductSkuVM> productSkuVMList, List<BrandVM> brandVMList, List<TypeVM> typeVMList, List<ScentVM> scentVMList, int page, String sort, int size) {
        this.productSkuVMList = productSkuVMList;
        this.brandVMList = brandVMList;
        this.typeVMList = typeVMList;
        this.scentVMList = scentVMList;
        this.page = page;
        this.sort = sort;
        this.size = size;
    }

    public ShopVM() {
    }

    public List<ProductSkuVM> getProductSkuVMList() {
        return this.productSkuVMList;
    }

    public List<BrandVM> getBrandVMList() {
        return this.brandVMList;
    }

    public List<TypeVM> getTypeVMList() {
        return this.typeVMList;
    }

    public List<ScentVM> getScentVMList() {
        return this.scentVMList;
    }

    public int getPage() {
        return this.page;
    }

    public String getSort() {
        return this.sort;
    }

    public int getSize() {
        return this.size;
    }

    public void setProductSkuVMList(List<ProductSkuVM> productSkuVMList) {
        this.productSkuVMList = productSkuVMList;
    }

    public void setBrandVMList(List<BrandVM> brandVMList) {
        this.brandVMList = brandVMList;
    }

    public void setTypeVMList(List<TypeVM> typeVMList) {
        this.typeVMList = typeVMList;
    }

    public void setScentVMList(List<ScentVM> scentVMList) {
        this.scentVMList = scentVMList;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
