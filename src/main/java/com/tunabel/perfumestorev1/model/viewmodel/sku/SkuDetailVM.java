package com.tunabel.perfumestorev1.model.viewmodel.sku;

import com.tunabel.perfumestorev1.model.viewmodel.common.HeaderMenuVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductImageVM;
import com.tunabel.perfumestorev1.model.viewmodel.common.ProductSkuVM;

import java.util.List;

public class SkuDetailVM {
    private String typeName;
    private int typeId;

    private String scentName;
    private int scentId;

    private String brandName;
    private int brandId;
    private String brandDesc;

    private int genderId;

    private String productName;
    private String productDesc;

    private int skuId;
    private String skuName;
    private String price;
    private int quantity;

    private List<ProductImageVM> productImageVMList;
    private List<ProductSkuVM> childrenSkuList;
    private List<ProductSkuVM> relatedSkuList;

    private HeaderMenuVM headerMenuVM;

    public SkuDetailVM(String typeName, int typeId, String scentName, int scentId, String brandName, int brandId, String brandDesc, int genderId, String productName, String productDesc, int skuId, String skuName, String price, int quantity, List<ProductImageVM> productImageVMList, List<ProductSkuVM> childrenSkuList, List<ProductSkuVM> relatedSkuList, HeaderMenuVM headerMenuVM) {
        this.typeName = typeName;
        this.typeId = typeId;
        this.scentName = scentName;
        this.scentId = scentId;
        this.brandName = brandName;
        this.brandId = brandId;
        this.brandDesc = brandDesc;
        this.genderId = genderId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.skuId = skuId;
        this.skuName = skuName;
        this.price = price;
        this.quantity = quantity;
        this.productImageVMList = productImageVMList;
        this.childrenSkuList = childrenSkuList;
        this.relatedSkuList = relatedSkuList;
        this.headerMenuVM = headerMenuVM;
    }

    public SkuDetailVM() {
    }


    public String getTypeName() {
        return this.typeName;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public String getScentName() {
        return this.scentName;
    }

    public int getScentId() {
        return this.scentId;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public int getBrandId() {
        return this.brandId;
    }

    public String getBrandDesc() {
        return this.brandDesc;
    }

    public int getGenderId() {
        return this.genderId;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDesc() {
        return this.productDesc;
    }

    public int getSkuId() {
        return this.skuId;
    }

    public String getSkuName() {
        return this.skuName;
    }

    public String getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public List<ProductImageVM> getProductImageVMList() {
        return this.productImageVMList;
    }

    public List<ProductSkuVM> getChildrenSkuList() {
        return this.childrenSkuList;
    }

    public List<ProductSkuVM> getRelatedSkuList() {
        return this.relatedSkuList;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setScentName(String scentName) {
        this.scentName = scentName;
    }

    public void setScentId(int scentId) {
        this.scentId = scentId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductImageVMList(List<ProductImageVM> productImageVMList) {
        this.productImageVMList = productImageVMList;
    }

    public void setChildrenSkuList(List<ProductSkuVM> childrenSkuList) {
        this.childrenSkuList = childrenSkuList;
    }

    public void setRelatedSkuList(List<ProductSkuVM> relatedSkuList) {
        this.relatedSkuList = relatedSkuList;
    }

    public HeaderMenuVM getHeaderMenuVM() {
        return headerMenuVM;
    }

    public void setHeaderMenuVM(HeaderMenuVM headerMenuVM) {
        this.headerMenuVM = headerMenuVM;
    }
}
