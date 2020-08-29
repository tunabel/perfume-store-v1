package com.tunabel.perfumestorev1.model.viewmodel.common;

public class ProductImageVM {
    private int id;
    private String imageURL;

    public ProductImageVM(int id, String imageURL) {
        this.id = id;
        this.imageURL = imageURL;
    }

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
}
