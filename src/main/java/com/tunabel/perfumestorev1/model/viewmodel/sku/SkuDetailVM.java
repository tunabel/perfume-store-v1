package com.tunabel.perfumestorev1.model.viewmodel.sku;

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

    public SkuDetailVM(String typeName, int typeId, String scentName, int scentId, String brandName, int brandId, String brandDesc, int genderId, String productName, String productDesc, int skuId, String skuName, String price, int quantity, List<ProductImageVM> productImageVMList, List<ProductSkuVM> childrenSkuList, List<ProductSkuVM> relatedSkuList) {
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
    }

    public SkuDetailVM() {
    }

    public static SkuDetailVMBuilder builder() {
        return new SkuDetailVMBuilder();
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

    public static class SkuDetailVMBuilder {
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

        SkuDetailVMBuilder() {
        }

        public SkuDetailVM.SkuDetailVMBuilder typeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder typeId(int typeId) {
            this.typeId = typeId;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder scentName(String scentName) {
            this.scentName = scentName;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder scentId(int scentId) {
            this.scentId = scentId;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder brandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder brandId(int brandId) {
            this.brandId = brandId;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder brandDesc(String brandDesc) {
            this.brandDesc = brandDesc;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder genderId(int genderId) {
            this.genderId = genderId;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder productDesc(String productDesc) {
            this.productDesc = productDesc;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder skuId(int skuId) {
            this.skuId = skuId;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder skuName(String skuName) {
            this.skuName = skuName;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder price(String price) {
            this.price = price;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder productImageVMList(List<ProductImageVM> productImageVMList) {
            this.productImageVMList = productImageVMList;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder childrenSkuList(List<ProductSkuVM> childrenSkuList) {
            this.childrenSkuList = childrenSkuList;
            return this;
        }

        public SkuDetailVM.SkuDetailVMBuilder relatedSkuList(List<ProductSkuVM> relatedSkuList) {
            this.relatedSkuList = relatedSkuList;
            return this;
        }

        public SkuDetailVM build() {
            return new SkuDetailVM(typeName, typeId, scentName, scentId, brandName, brandId, brandDesc, genderId, productName, productDesc, skuId, skuName, price, quantity, productImageVMList, childrenSkuList, relatedSkuList);
        }

        public String toString() {
            return "SkuDetailVM.SkuDetailVMBuilder(typeName=" + this.typeName + ", typeId=" + this.typeId + ", scentName=" + this.scentName + ", scentId=" + this.scentId + ", brandName=" + this.brandName + ", brandId=" + this.brandId + ", brandDesc=" + this.brandDesc + ", genderId=" + this.genderId + ", productName=" + this.productName + ", productDesc=" + this.productDesc + ", skuId=" + this.skuId + ", skuName=" + this.skuName + ", price=" + this.price + ", quantity=" + this.quantity + ", productImageVMList=" + this.productImageVMList + ", childrenSkuList=" + this.childrenSkuList + ", relatedSkuList=" + this.relatedSkuList + ")";
        }
    }
}
