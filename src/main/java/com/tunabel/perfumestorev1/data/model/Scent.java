package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dbo_product_scent")
public class Scent {

    @Column(name = "scent_id")
    @Id
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scent")
    private List<Product> listProducts = new ArrayList<>();

    private String name;

    public int getId() {
        return this.id;
    }

    public List<Product> getListProducts() {
        return this.listProducts;
    }

    public String getName() {
        return this.name;
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
}
