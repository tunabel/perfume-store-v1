package com.tunabel.perfumestorev1.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dbo_cart")
public class Cart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    @Id
    private int id;

    private String guid;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartSku> cartSkuList = new ArrayList<>();

    public int getId() {
        return this.id;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getUsername() {
        return this.username;
    }

    public List<CartSku> getCartSkuList() {
        return this.cartSkuList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCartSkuList(List<CartSku> cartSkuList) {
        this.cartSkuList = cartSkuList;
    }
}
