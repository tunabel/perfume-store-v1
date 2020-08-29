package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dbo_product_image")
public class ProductImage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    @Id
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
