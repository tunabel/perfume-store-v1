package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dbo_product_sku")
public class ProductSku {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sku_id")
    @Id
    private int id;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;

    @Column(name = "main_sku")
    private int mainSku;

    private int price;
    private int quantity;
    private int volume;

    private String spec;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return this.id;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getName() {
        return this.name;
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

    public String getSpec() {
        return this.spec;
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

    public void setName(String name) {
        this.name = name;
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

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
