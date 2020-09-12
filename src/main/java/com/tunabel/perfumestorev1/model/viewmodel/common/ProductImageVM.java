package com.tunabel.perfumestorev1.model.viewmodel.common;

import java.util.Date;

public class ProductImageVM {
    private int id;
    private String imageURL;
    private Date createdDate;

    public ProductImageVM() {
    }

    public int getId() {
        return this.id;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
