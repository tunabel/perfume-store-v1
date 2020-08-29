package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dbo_product_brand")
public class Brand {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
    private List<Product> listProducts = new ArrayList<>();

    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return this.id;
    }

    public List<Product> getListProducts() {
        return this.listProducts;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
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

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
