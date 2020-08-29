package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_product")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @Id
    private int id;


    @Column(name = "type_id", insertable = false, updatable = false)
    private Integer typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name = "scent_id", insertable = false, updatable = false)
    private Integer scentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scent_id")
    private Scent scent;

    @Column(name = "brand_id", insertable = false, updatable = false)
    private Integer brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductSku> productSkuList = new ArrayList<>();

    private String name;

    private String description;

    private int gender;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return this.id;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public Type getType() {
        return this.type;
    }

    public Integer getScentId() {
        return this.scentId;
    }

    public Scent getScent() {
        return this.scent;
    }

    public Integer getBrandId() {
        return this.brandId;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public List<ProductImage> getProductImageList() {
        return this.productImageList;
    }

    public List<ProductSku> getProductSkuList() {
        return this.productSkuList;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getGender() {
        return this.gender;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setScentId(Integer scentId) {
        this.scentId = scentId;
    }

    public void setScent(Scent scent) {
        this.scent = scent;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    public void setProductSkuList(List<ProductSku> productSkuList) {
        this.productSkuList = productSkuList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
