package com.tunabel.perfumestorev1.model.viewmodel.common;

public class ProductSearchVM {
    private int[] type;
    private int[] scent;
    private int[] brand;
    private int[] gender;
    private String name;

    public ProductSearchVM(int[] type, int[] scent, int[] brand, int[] gender, String name) {
        this.type = type;
        this.scent = scent;
        this.brand = brand;
        this.gender = gender;
        this.name = name;
    }

    public ProductSearchVM() {
    }

    public int[] getType() {
        return this.type;
    }

    public int[] getScent() {
        return this.scent;
    }

    public int[] getBrand() {
        return this.brand;
    }

    public int[] getGender() {
        return this.gender;
    }

    public String getName() {
        return this.name;
    }

    public void setType(int[] type) {
        this.type = type;
    }

    public void setScent(int[] scent) {
        this.scent = scent;
    }

    public void setBrand(int[] brand) {
        this.brand = brand;
    }

    public void setGender(int[] gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ProductSearchVM(type=" + java.util.Arrays.toString(this.getType()) + ", scent=" + java.util.Arrays.toString(this.getScent()) + ", brand=" + java.util.Arrays.toString(this.getBrand()) + ", gender=" + java.util.Arrays.toString(this.getGender()) + ", name=" + this.getName() + ")";
    }
}
