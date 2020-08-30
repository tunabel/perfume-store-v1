package com.tunabel.perfumestorev1.model.viewmodel.common;

public class ProductSkuVM {
    private int id;
    private String name;
    private String brand;
    private int brandId;
    private String spec;
    private int mainSku;
    private String price;
    private int quantity;
    private int volume;
    private String imageURL;
    private int status;

    public ProductSkuVM(int id, String name, String brand, int brandId, String spec, int mainSku, String price, int quantity, int volume, String imageURL, int status) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.brandId = brandId;
        this.spec = spec;
        this.mainSku = mainSku;
        this.price = price;
        this.quantity = quantity;
        this.volume = volume;
        this.imageURL = imageURL;
        this.status = status;
    }

    public ProductSkuVM() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getSpec() {
        return this.spec;
    }

    public int getMainSku() {
        return this.mainSku;
    }

    public String getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getVolume() {
        return this.volume;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public int getStatus() {
        return this.status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setMainSku(int mainSku) {
        this.mainSku = mainSku;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
