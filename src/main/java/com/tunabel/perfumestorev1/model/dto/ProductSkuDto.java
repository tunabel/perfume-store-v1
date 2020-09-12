package com.tunabel.perfumestorev1.model.dto;

import com.tunabel.perfumestorev1.data.model.Product;

import javax.validation.constraints.Null;
import java.util.Date;

public class ProductSkuDto {

    @Null
    private int id;

    @Null
    private Integer productId;

    private Product product;

    private String spec;
    private String name;
    private int mainSku;

    private int price;
    private int quantity;
    private int volume;

    private String imageURL;

    @Null
    private Date createdDate;


    public ProductSkuDto() {
    }

    public ProductSkuDto(int id, Integer productId, Product product, String spec, String name, int mainSku, int price, int quantity, int volume, String imageURL, Date createdDate) {
        this.id = id;
        this.productId = productId;
        this.product = product;
        this.spec = spec;
        this.name = name;
        this.mainSku = mainSku;
        this.price = price;
        this.quantity = quantity;
        this.volume = volume;
        this.imageURL = imageURL;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getSpec() {
        return this.spec;
    }

    public int getMainSku() {
        return this.mainSku;
    }

    public int getPrice() {
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

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setMainSku(int mainSku) {
        this.mainSku = mainSku;
    }

    public void setPrice(int price) {
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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
